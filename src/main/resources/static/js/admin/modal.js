export class Modal {
    prefix = '';
    bodyContent = '';
    onClose = () => {
    };
    onOpen = () => {
    };

    constructor(prefix, bodyContent) {
        this.prefix = prefix;
        this.bodyContent = bodyContent;
    }

    open = () => {
        let holder = document.getElementById('modal-holder');
        holder.innerHTML = this.getModal();
        let modal = document.getElementById(this.prefix + '-modal')
        modal.onclick = (event) => {
            if (event.target === modal) {
                this.close();
            }
        }
    }

    getModal = () => {
        return `<div id="${this.prefix + '-modal'}" class="modal">
                    ${this.bodyContent}
                </div>`;
    }

    close = () => {
        let holder = document.getElementById('modal-holder');
        holder.innerHTML = '';
    }
}



