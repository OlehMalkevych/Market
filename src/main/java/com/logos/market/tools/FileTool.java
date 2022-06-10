package com.logos.market.tools;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@Component
public class FileTool {

///Users/olegmalkevuch/Desktop/LogosProject/src/main/resources/static
	public static final String IMG_DIR =
			System.getProperty("user.home") + File.separator
					+ "Desktop" + File.separator
					+ "LogosProject" + File.separator
					+ "src" + File.separator
					+ "main" + File.separator
					+ "resources" + File.separator
					+ "static" + File.separator
					+ "images" + File.separator;
//
//	//	D:\UserFiles\Logos_17_30\images\
//	public static final String USER_DIR =
//			"D:" + File.separator +
//					"UserFiles" + File.separator +
//					"Logos_17_30" + File.separator +
//					"images" + File.separator;

	public String saveFile(String img) throws IOException {

		createDir(IMG_DIR);//create folder if not exists

		// ["data:image/jpeg;base64" "/9j/4AAQSkZJRgABAQEAYABgAAD//gA7Q1"]
		String[] data = img.split(",");
		String metaInfo = data[0];
		String base64File = data[1];

		String fileName = createFileName(null, getFileExtensionFromMetaInfo(metaInfo));

		Files.write(
				Paths.get(IMG_DIR, fileName),
				Base64.getDecoder().decode(base64File.getBytes())
		);
		return fileName;
	}

	private String createFileName(String fileName, String fileExtension) {
		if (fileName == null) {
			fileName = UUID.randomUUID().toString();
		}
		return String.format("%s.%s", fileName, fileExtension);
	}

	//data:image/jpeg;base64
	//jpeg;base64
	//jpeg
	private String getFileExtensionFromMetaInfo(String metaInfo) {
		return metaInfo.split("/")[1].split(";")[0];
	}

	private void createDir(String dir) {
		File file = new File(dir);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

}
