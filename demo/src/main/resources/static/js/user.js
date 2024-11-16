function deleteUser(userId, buttonElement) {
    console.log("Delete button clicked for user:", userId);  // Log user ID

    if (confirm('Are you sure you want to delete this user?')) {
        fetch(`/users/${userId}`, {
            method: 'DELETE'
        })
        .then(response => {
            console.log("Response status:", response.status);  // Log response status
            console.log(userId)

            if (response.ok) {
                console.log(`User ${userId} deleted successfully`);

                // Find the closest parent <tr> or <div> to the button that was clicked
                let userRow = buttonElement.closest('tr'); // Use 'tr' for table rows or 'div' for divs
                if (userRow) {
                userRow.remove();  // Remove the user row from the DOM
                } else {
                    console.error(`Row for user ${userId} not found`);
                    }
                } else {
                            alert('Failed to delete user');
                        }
        })
        .catch(error => {
            console.error('Error during delete request:', error);
            alert('An error occurred while trying to delete the user');
        });
    }
}