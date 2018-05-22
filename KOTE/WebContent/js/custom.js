
/*Forgot Password Form validation                                                                  - Forgot Password Form validation
-------------------------------------------------------------------------------------------------------------------------------------*/
function forgotpswdval(){
$(".forgotpswd").validate({
rules: {
	usernm: {
 		required: true,
 	}
},
messages: {
    usernm: {
         required:"",
    }
}
});
}

/*Change Password Form validation                                                                  - Change Password Form validation
-------------------------------------------------------------------------------------------------------------------------------------*/
function changepswdval(){
$("#change-password-form").validate({
rules: {
	password: {
		required: true,
		minlength:"8",
		maxlength:"15"
	},

	confirmpassword: {
		required: true,
		equalTo: "#password"
	},
},
messages: {
	password: {
	required:"",
	},
	confirmpassword: {
	required:"",
	},
	}
});
}

/*Login page Form validation                                                                     - Login page Form validation
-------------------------------------------------------------------------------------------------------------------------------------*/
function loginval(){
	$("#login").validate({
rules: {
	password: {
	required: true,
	minlength:"8",
	maxlength:"15"
 },

 usernm: {
 required: true,
 },

},
messages: {
 password: {
 required:"",
 },
 usernm: {
 required:"",
 },
 }
 });
}

/*Change and Reset password page script for eye and eye slash icon toggle                       - Change and Reset password page script
------------------------------------------------------------------------------------------------------------------------------*/
$( document ).ready(function() {
$(".passhideshow").click(function () {
	$(this).toggleClass("eyeclose").addClass("eyeopen");
	$(this).removeClass("eyeopen");

	if ($(".passhidetext").attr("type")=="password")
	{ 
		$(".passhidetext").attr("type", "text");
	}
	else{
		$(".passhidetext").attr("type", "password");
	}
});

$(".passhideshow1").click(function () {
	$(this).toggleClass("eyeclose").addClass("eyeopen");
	$(this).removeClass("eyeopen");

	if ($(".passhidetext1").attr("type")=="password")
	{ 
		$(".passhidetext1").attr("type", "text");
	}
	else{
		$(".passhidetext1").attr("type", "password");
	}
});

$(".success-notification").fadeOut(5000);
$(".failure-notification").fadeOut(5000);

});


/*CINE SPOCS - number only accept for the fields script below      CINE SPOCS - number only accept for the fields script below
------------------------------------------------------------------------------------------------------------------------------*/
$(function(){      
 $('.number-only').keyup(function(e) {
  if(this.value!='-')
    while(isNaN(this.value))
   this.value = this.value.split('').reverse().join('').replace(/[\D]/i,'')
           .split('').reverse().join('');
})
.on("cut copy paste",function(e){
 e.preventDefault();
});
});

/* TextNumberBoth only script
================================================*/
$(function(){  
$('.textnumber').keydown(function(e) {
        if (!((key == 8) || (key == 9) || (key == 32) || (key == 190) || (key == 46) || (key == 17) || (key >= 48 && key <= 57) || (key >= 96 && key <= 105) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
          e.preventDefault();
        }
    });
});

/*text only script
================================================*/
$(function(){  
$('.txtOnly').keydown(function(e) {
      if (e.altKey) {
        e.preventDefault();
      } else {
        var key = e.keyCode;
        if (!((key == 8) || (key == 9) || (key == 190) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
          e.preventDefault();
        }
      }
    });
});

<!--Email must be an email -->
$(function(){ 
	$('.email_valid').on('input', function() {
		var input=$(this);
		var re = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
		var is_email=re.test(input.val());
		if(is_email){input.removeClass("invalid").addClass("valid");}
		else{input.removeClass("valid").addClass("invalid");}
	});
});
/*Date Of Birth - accept number with "/" symbol only script below                      Date Of Birth - accept number with "/" symbol only script below
------------------------------------------------------------------------------------------------------------------------------------------------------*/
$(function(){  
$('.dob-only').keydown(function(e) {
		if ((e.shiftKey || e.altKey || (key >= 66 && key <= 90))) {
			e.preventDefault();
		}
		else {
			var key = e.keyCode;
			if (!(e.ctrlKey || (key == 9) || (key == 111) || (key == 191) || (key == 8) || (key == 35) || (key == 36) || (key == 37) || (key == 39) || (key == 65) || (key >= 48 && key <= 57) || (key >= 96 && key <= 105))) {
			  e.preventDefault();
			}
		}
    })
	.on("cut copy paste",function(e){
	 e.preventDefault();
	});
});