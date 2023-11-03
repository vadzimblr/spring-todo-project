document.body.addEventListener('click', (event) => {
    if (event.target.matches('.to-do-checkbox')) {
        let checkbox = event.target;
        if (checkbox.checked) {
            let taskId = checkbox.id.split('_')[1];
            let csrfToken = $("meta[name='_csrf']").attr("content");
            let config = {
                duration: 3,
                position: 'top-right',
                stopOnHover: false,
                hasCloseButton: false
            }
            toast.configure(config)
            $.ajax({
                type: 'POST',
                url: `/todo/setCompleted`,
                data: JSON.stringify({ taskId:taskId }),
                contentType: 'application/json',
                headers: {
                    'X-CSRF-TOKEN': csrfToken
                },
                success: function(response) {
                    checkbox.closest('.list-group-item').remove();
                    toast.success("The task is marked completed")
                },
                error: function() {
                    toast.success("Something went wrong")
                }
            });
        }
    }
});
