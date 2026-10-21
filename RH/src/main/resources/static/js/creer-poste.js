document.addEventListener('DOMContentLoaded', () => {
    const formsContainer = document.querySelector('#position-forms');
    const addFormButton = document.querySelector('#add-position-form');

    if (!formsContainer || !addFormButton) {
        return;
    }

    formsContainer.addEventListener('click', (event) => {
        const removeButton = event.target.closest('.remove-position-form');

        if (!removeButton) {
            return;
        }

        const form = removeButton.closest('.position-form');
        if (formsContainer.querySelectorAll('.position-form').length > 1) {
            form.remove();
        }
    });

    addFormButton.addEventListener('click', () => {
        const firstForm = formsContainer.querySelector('.position-form');
        const newForm = firstForm.cloneNode(true);
        const formNumber = formsContainer.querySelectorAll('.position-form').length + 1;

        newForm.id = `position-form-${formNumber}`;
        newForm.querySelector('.position-form-number').textContent = `Poste ${formNumber}`;

        const removeButton = document.createElement('button');
        removeButton.className = 'button button-danger remove-position-form';
        removeButton.type = 'button';
        removeButton.setAttribute('aria-label', 'Supprimer ce formulaire');
        removeButton.title = 'Supprimer ce formulaire';
        removeButton.textContent = 'Supprimer';
        newForm.querySelector('.position-form-header').appendChild(removeButton);

        newForm.querySelectorAll('[id]').forEach((element) => {
            const oldId = element.id;
            const newId = `${oldId}-${formNumber}`;
            const label = newForm.querySelector(`label[for="${oldId}"]`);

            element.id = newId;
            if (label) {
                label.htmlFor = newId;
            }
        });

        newForm.querySelectorAll('input:not([readonly]), textarea').forEach((field) => {
            field.value = '';
        });
        newForm.querySelectorAll('select').forEach((field) => {
            field.selectedIndex = 0;
        });

        formsContainer.appendChild(newForm);
        newForm.querySelector('input:not([readonly])').focus();
    });
});
