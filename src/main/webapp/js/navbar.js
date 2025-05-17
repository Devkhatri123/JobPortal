/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


document.addEventListener('DOMContentLoaded', function() {
  // Menu toggle functionality
  const menuToggle = document.getElementById('menu-toggle');
  const menuClose = document.getElementById('menu-close');
  const mobileMenu = document.getElementById('mobile-menu');
  
  menuToggle.addEventListener('click', function() {
    mobileMenu.classList.add('open');
  });
  
  menuClose.addEventListener('click', function() {
    mobileMenu.classList.remove('open');
  });
  
  // Close menu when clicking outside
  document.addEventListener('mousedown', function(event) {
    if (mobileMenu.classList.contains('open') && 
        !mobileMenu.contains(event.target) && 
        !menuToggle.contains(event.target)) {
      mobileMenu.classList.remove('open');
    }
  });
  
  // Dropdown functionality (for future enhancement)
  const dropdownItems = document.querySelectorAll('.mobile-menu-item, .nav-item.dropdown');
  
  dropdownItems.forEach(item => {
    item.addEventListener('click', function() {
      // This could be expanded to show dropdown content
      console.log('Dropdown clicked:', this.querySelector('span').textContent);
    });
  });
});