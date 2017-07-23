

jQuery.fn.centerModal = function () {

	this.css("left", Math.max(0, (($(window).width() - $(this).outerWidth()) / 2) +
		$(window).scrollLeft()) + "px");
	return this;
}

jQuery(document).ready(function ($) {

	var $document = $(document),
		$element = $('.searchFormCustom'),
		$element2 = $('.searchFormCustom .form-body'),
		$element3 = $('.searchFormCustom .form-actions'),
		$element4 = $('.medecinCustom'),
		$element5 = $('.ouSeparator'),
		$element6 = $('.specialite'),
		$element7 = $('.adresse'),
		$element8 = $('.dateHeure'),
		$element9 = $('.form-group.last.lastCustom'),
		$element10 = $('.searchFormCustom .portlet-title'),
		$element11 = $('.searchFormCustom .portlet-body'),
		className = 'collapsed-form',
		className2 = 'col-md-10',
		className3 = 'col-md-2',
		className4 = 'col-md-4',
		className5 = 'col-md-3',
		className6 = 'col-md-6',
		className7 = 'container'
		;



	$document.scroll(function () {
		if ($(window).width() > 991) {
			/*$element10.toggleClass(className7, $document.scrollTop() >= 400);
			$element11.toggleClass(className7, $document.scrollTop() >= 400);
			$element.toggleClass(className, $document.scrollTop() >= 400);
			$element2.toggleClass(className2, $document.scrollTop() >= 400);
			$element3.toggleClass(className3, $document.scrollTop() >= 400);
			$element4.toggleClass(className4, $document.scrollTop() >= 400);
			$element5.toggleClass(className3, $document.scrollTop() >= 400);
			$element6.toggleClass(className5, $document.scrollTop() >= 400);
			$element7.toggleClass(className5, $document.scrollTop() >= 400);
			$element8.toggleClass(className6, $document.scrollTop() >= 400);
			$element9.toggleClass(className6, $document.scrollTop() >= 400);*/
		}

	});
	//trigger the animation - open modal window

	$('.customBTN').on('click', function () {
		var closeBTN = $(this).attr('data-hide');
		$("#" + closeBTN + " .close").click();
	});

	/*$('.btn-consult-detail').on('click', function () {
		if ($('body').scrollTop(0)) {
			$('body').css('overflow', 'hidden');
			$('.modaldetails').show();
			$(window).resize();
		}

	});*/
	
	$('.cd-modal-close').on('click', function () {
		$('body').css('overflow', 'visible');
		$('.modaldetails').hide();
	});
	$('.scrollableResult').mCustomScrollbar();

	$('[data-type="modal-trigger"]').on('click', function () {
		var actionBtn = $(this),
			scaleValue = retrieveScale(actionBtn.next('.cd-modal-bg'));

		actionBtn.addClass('to-circle');
		actionBtn.next('.cd-modal-bg').addClass('is-visible').one('webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend', function () {
			animateLayer(actionBtn.next('.cd-modal-bg'), scaleValue, true);
		});

		//if browser doesn't support transitions...
		if (actionBtn.parents('.no-csstransitions').length > 0) animateLayer(actionBtn.next('.cd-modal-bg'), scaleValue, true);

		$('.cd-modal').centerModal();
		$('.cd-modal').height($(window).height() - 120);
	});

	//trigger the animation - close modal window
	$('.cd-section .cd-modal-close').on('click', function () {
		closeModal();
	});
	$(document).keyup(function (event) {
		if (event.which == '27') closeModal();
	});

	$(window).on('resize', function () {
		//on window resize - update cover layer dimention and position
		if ($('.cd-section.modal-is-visible').length > 0) window.requestAnimationFrame(updateLayer);
		$('.cd-modal').centerModal();

		if ($(window).width() < 991) {
			/*$element.removeClass(className);
			$element2.removeClass(className2);
			$element3.removeClass(className3);
			$element4.removeClass(className4);
			$element5.removeClass(className3);
			$element6.removeClass(className5);
			$element7.removeClass(className5);
			$element8.removeClass(className6);
			$element9.removeClass(className6);
			$element10.removeClass(className7);
			$element11.removeClass(className7);*/
		}

	});

	function retrieveScale(btn) {
		var btnRadius = btn.width() / 2,
			left = btn.offset().left + btnRadius,
			top = btn.offset().top + btnRadius - $(window).scrollTop(),
			scale = scaleValue(top, left, btnRadius, $(window).height(), $(window).width());

		btn.css('position', 'fixed').velocity({
			top: top - btnRadius,
			left: left - btnRadius,
			translateX: 0,
		}, 0);

		return scale;
	}

	function scaleValue(topValue, leftValue, radiusValue, windowW, windowH) {
		var maxDistHor = (leftValue > windowW / 2) ? leftValue : (windowW - leftValue),
			maxDistVert = (topValue > windowH / 2) ? topValue : (windowH - topValue);
		return Math.ceil(Math.sqrt(Math.pow(maxDistHor, 2) + Math.pow(maxDistVert, 2)) / radiusValue);
	}

	function animateLayer(layer, scaleVal, bool) {
		$('.cd-modal-bg').animate({ opacity: "1" }, 0)
		layer.velocity({ scale: scaleVal }, 400, function () {
			$('body').toggleClass('overflow-hidden', bool);
			(bool)
				? layer.parents('.cd-section').addClass('modal-is-visible').end().off('webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend')
				: layer.removeClass('is-visible').removeAttr('style').siblings('[data-type="modal-trigger"]').removeClass('to-circle');
		});
		setTimeout(function () {
			$('.cd-modal-bg').animate({ opacity: "0" }, 200)

		}, 300)
	}

	function updateLayer() {
		var layer = $('.cd-section.modal-is-visible').find('.cd-modal-bg'),
			layerRadius = layer.width() / 2,
			layerTop = layer.siblings('.btn').offset().top + layerRadius - $(window).scrollTop(),
			layerLeft = layer.siblings('.btn').offset().left + layerRadius,
			scale = scaleValue(layerTop, layerLeft, layerRadius, $(window).height(), $(window).width());

		layer.velocity({
			top: layerTop - layerRadius,
			left: layerLeft - layerRadius,
			scale: scale,
		}, 0);
	}

	function closeModal() {
		var section = $('.cd-section.modal-is-visible');
		section.removeClass('modal-is-visible').one('webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend', function () {
			animateLayer(section.find('.cd-modal-bg'), 1, false);
		});
		//if browser doesn't support transitions...
		if (section.parents('.no-csstransitions').length > 0) animateLayer(section.find('.cd-modal-bg'), 1, false);
		$('.cd-modal-bg').animate({ opacity: "1" }, 200)
	}
});
