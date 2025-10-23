// rutinas.js: preview de imagen y modal confirm
function mostrarImagen(input) {
    if (!input || !input.files || !input.files[0]) return;
    const file = input.files[0];
    if (!file.type.startsWith('image/')) return;
    const reader = new FileReader();
    reader.onload = function (e) {
        const form = input.closest('form') || document;
        const img = form.querySelector('.preview-img');
        if (img) {
            img.src = e.target.result;
            img.style.display = 'block';
        }
    };
    reader.readAsDataURL(file);
}

document.addEventListener('DOMContentLoaded', function () {
    const confirmModal = document.getElementById('confirmModal');
    if (!confirmModal) return;
    confirmModal.addEventListener('show.bs.modal', function (event) {
        const button = event.relatedTarget;
        if (!button) return;
        const id = button.getAttribute('data-id') || button.getAttribute('data-id');
        const titulo = button.getAttribute('data-titulo') || button.getAttribute('data-titulo');
        const modalId = document.getElementById('modalId');
        const modalTitulo = document.getElementById('modalTitulo');
        if (modalId) modalId.value = id;
        if (modalTitulo) modalTitulo.textContent = titulo;
    });
});