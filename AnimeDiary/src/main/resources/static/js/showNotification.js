function showNotification(message) {
    var notification = $("#notification");
    notification.html(message);
    notification.fadeIn().delay(5000).fadeOut(); // Показать на 5 секунд
}