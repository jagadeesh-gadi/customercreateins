$('.dropdown-submenu a.dropdown-toggle').on("click", function(e) {
	    // Close all open submenus
	    $('.dropdown-submenu .dropdown-menu').hide();

	    // Open the clicked submenu
	    $(this).next('ul').toggle();

	    e.stopPropagation(); // Prevents the click from bubbling up
	    e.preventDefault();  // Prevents the default link behavior
	});
	
	
	async function login(event) {
	    event.preventDefault();
	    const email = document.getElementById('email').value;
	    const password = document.getElementById('password').value;
	    const role = document.getElementById('role').value;

	    const response = await fetch('/api/auth/login', {
	        method: 'POST',
	        headers: {
	            'Content-Type': 'application/json',
	        },
	        body: JSON.stringify({ email, password, role }),
	    });

	    if (response.redirected) {
	        window.location.href = response.url; // Redirect to the URL returned by the server
	    } else {
	        const errorData = await response.text();
	        alert(errorData); // Show error message if not redirected
	    }
	}

	async function register(event) {
	    event.preventDefault();
	    const email = document.getElementById('email').value;
	    const password = document.getElementById('password').value;
	    const role = document.getElementById('role').value;

	    const response = await fetch('/api/auth/register', {
	        method: 'POST',
	        headers: {
	            'Content-Type': 'application/json',
	        },
	        body: JSON.stringify({ email, password, role }),
	    });

	    if (response.redirected) {
	        window.location.href = response.url; // Redirect to the URL returned by the server
	    } else {
	        const errorData = await response.text();
	        alert(errorData); // Show error message if not redirected
	    }
	}
	
	
/*	function validateLoginForm() {
	    // Get input values
	    const username = document.getElementById('username').value.trim();
	    const password = document.getElementById('password').value.trim();
	    
	    // Get error message elements
	    const usernameError = document.getElementById('username-error');
	    const passwordError = document.getElementById('password-error');
	    const matchError = document.getElementById('error-message');

	    // Reset error messages
	    usernameError.style.display = 'none';
	    passwordError.style.display = 'none';
	    matchError.style.display = 'none';

	    // Validation flags
	    let isValid = true;

	    // Basic field validation
	    if (username.length < 3) {
	        usernameError.style.display = 'block';
	        isValid = false;
	    }
	    if (password.length < 6) {
	        passwordError.style.display = 'block';
	        isValid = false;
	    }

	    // Get stored user details from localStorage
	    const registeredUsername = localStorage.getItem('registeredUsername');
	    const registeredPassword = localStorage.getItem('registeredPassword');

	    // Check if user details match with registered details
	    if (isValid && (username !== registeredUsername || password !== registeredPassword)) {
	        matchError.textContent = 'Invalid username or password. Please check your credentials.';
	        matchError.style.display = 'block';
	        isValid = false;
	    }

	    // If validation fails, reset the form fields for a fresh attempt
	    if (!isValid) {
	        document.getElementById('username').value = '';
	        document.getElementById('password').value = '';
	    }

	    return isValid; // Prevent form submission if not valid
	}
*/
	
		// Toggle the password visibility
		        document.getElementById("togglePassword").addEventListener("click", function() {
		            var passwordField = document.getElementById("password");
		            var type = passwordField.type === "password" ? "text" : "password";
		            passwordField.type = type;
		            this.classList.toggle("fa-eye-slash");
		        });


