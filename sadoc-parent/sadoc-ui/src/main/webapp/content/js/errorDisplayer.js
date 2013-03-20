$(document).ready(function() {

	$('.wwFormTable tbody tr').each(function(i, e) {
		if ($(this).attr('errorfor')) {
			var copy_to = $(this).next().clone(true);
			var copy_from = $(this).clone(true);
			$(this).next().replaceWith(copy_from);
			$(this).replaceWith(copy_to);
			copy_from.hide();
			copy_from.show("shake", {}, 5000);
		}
	});
});