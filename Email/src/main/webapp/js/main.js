var message = new Object();
var header, token, action;
function theFocus(obj) {
	 $('[data-toggle="tooltip"]').tooltip(); 
}

function send(action, virtualMessage) {
	$.ajax({
		type : "POST",
		url : action,
		data : virtualMessage,
		processData : false,
		contentType : false,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success : function(resultData) {
			$('#dynamic').html(resultData).css("display","block").fadeOut(3000);
		},
		error : function(resultData) {
			$('#dynamic').html(resultData).css("display","block").fadeOut(3000);
		}
	});
}

function showit(id) {
	$('#my' + id).parent().children().each(function(index) {
		if (index % 2 != 0 && $(this).attr('id').localeCompare(id)!=0) {
			$(this).css('display', 'none');
		}
	});
	$('#' + id).toggle();
	if ($("#my" + id).attr('class').toUpperCase().localeCompare(
			"unread".toUpperCase()) == 0) {
		var selectedItems = [];
		selectedItems.push(id);
		$.ajax({
			type : "POST",
			url : "/Email/updatestatus",
			data : {
				myArray : selectedItems,
				status : "READ"
			},
			beforeSend : function(xhr) {
				xhr.setRequestHeader(header, token);
			}
		});
		$("#my" + id).attr('class', 'read');
	}

}

$(document).ready(
		function() {

			/* summernote js starts */
			$('#summernote').summernote({
				height : ($(window).height() - 300),
				callbacks : {
					onImageUpload : function(image) {
						for (i = 0; i < image.length; i++)
							uploadImage(image[i]);
					}
				}
			});

			function uploadImage(image) {
				var data = new FormData();
				data.append("files[]", image);
				$.ajax({
					url : '/Email/saveimage',
					cache : false,
					contentType : false,
					processData : false,
					data : data,
					type : "post",
					beforeSend : function(xhr) {
						xhr.setRequestHeader(header, token);
					},
					success : function(url) {
						var image = $('<img>').attr('src', url);
						$('#summernote').summernote("insertNode", image[0]);
					},
					error : function(data) {
						console.log(data);
					}
				});
			}

			/* summernote js ends */

			/* file attachment js start */
			var virtualMessage = new FormData();
			token = $("meta[name='_csrf']").attr("content");
			header = $("meta[name='_csrf_header']").attr("content");
			$("#myFile").change(function() {
				virtualMessage = listFiles();
			});
			$("#messageForm").submit(
					function(event) {

						// Prevent the form from submitting via the browser.
						event.preventDefault();
						virtualMessage.set("message",null)
						message.messageBody = $('#summernote').summernote(
								'code');
						message.subject = $('#subject').val();
						message.receiverId = $('#receiverMail').val();
						action = $('#messageForm').attr('action');
						virtualMessage.append("message", new Blob([ JSON
								.stringify(message) ], {
							type : "application/json"
						}));
						/*console.log("body="+message.messageBody+" subject"+message.subject+" receiver"+message.receiverId)*/
						send(action, virtualMessage);
					});

			/* file attachment js ends */
			$("td .mail-checkbox").click(function(e) {
				e.stopPropagation(); // don't run parent onclick
			});
			$("#allornone").click(function(e){
				if($(this).is(':checked'))
				{		
					$('.read').find('input[type="checkbox"]').each(function() {
						$(this).prop('checked', true);
					});
					$('.unread').find('input[type="checkbox"]').each(function() {
						$(this).prop('checked', true);
					});
				}
				else
					none()
			});
		});

function listFiles() {
	var virtualMessage = new FormData();
	var x = document.getElementById("myFile");
	var txt = "";
	if ('files' in x) {
		if (x.files.length != 0) {
			for (var i = 0; i < x.files.length; i++) {
				var file = x.files[i];
				virtualMessage.append('files[]', file);
				if ('name' in file) {
					txt += "<div class='col-sm-6'>" + file.name + "</div>";
				}

			}
		}
	} else {
		if (x.value != "") {
			txt += "The files upload feature is not supported by your browser!";
		}
	}
	document.getElementById("uploadFileSection").innerHTML = txt;
	return virtualMessage;
}

function read(myOption) {
	$('.read').find('input[type="checkbox"]').each(function() {
		$(this).prop('checked', myOption);
	});
	if (myOption)
		unread(!myOption)
}

function unread(myOption) {
	$('.unread').find('input[type="checkbox"]').each(function() {
		$(this).prop('checked', myOption);
	});
	if (myOption)
		read(!myOption)
}
function none() {
	unread(false);
	read(false);
}

function changeStatus(option) {
	var selectedItems = [];
	action = "/Email/updatestatus";
	if(option==="READ"){
		$('.unread').find('input:checked').prev().each(function() {
			$("#my"+$(this).attr('value')).removeClass('unread')
			$("#my"+$(this).attr('value')).addClass('read')
			selectedItems.push($(this).attr('value'));
			});
	}
	else if(option==="UNREAD"){
		$('.read').find('input:checked').prev().each(function() { 
			$("#my"+$(this).attr('value')).removeClass('read')
			$("#my"+$(this).attr('value')).addClass('unread')
			selectedItems.push($(this).attr('value'));
			});
	}
	else {
		$('.unread, .read').find('input:checked').prev().each(function() {
			var id = $(this).attr('value');
			selectedItems.push(id);
			$("#" + "my" + id).remove();
			$("#" + id).remove();
		});
	}
	sendStatus(selectedItems, action, option);
}
function sendStatus(selectedItems, action, option) {
	$.ajax({
		type : "POST",
		url : action,
		data : {
			myArray : selectedItems,
			status : option
		},
		beforeSend : function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success : function(resultData) {
			$('#dynamicstatus').html(resultData).css("display","inline").fadeOut(3000);
		},
		error : function(resultData) {
			$('#dynamicstatus').html(resultData).css("display","inline").fadeOut(3000);
		}
	});
}