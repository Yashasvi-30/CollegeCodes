const signupForm = document.getElementById('signup-form');

signupForm.addEventListener('submit', (event) => {
  event.preventDefault(); // Prevent default form submission

  const name = document.getElementById('name').value;
  const email = document.getElementById('email').value;
  const password = document.getElementById('password').value;

  // Send data to Node.js backend using AngularJS service (code not shown here)
  // ...

  // Handle response from backend (code not shown here)
  // ...
});
