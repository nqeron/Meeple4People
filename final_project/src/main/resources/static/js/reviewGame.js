/**
 * 
 */

function validateForm(){
	var comment = document.forms["commentForm"]["commentText"].value;
	if(comment == ""){
		alert("Please fill out a comment - it helps a lot!");
		return false;
	}
	
	if(comment.length > 280){
		alert("Your comment cannot have more than 280 characters!");
		return false;
	}
	
	return true;
	
}

$(document).ready( function() {
	$('.starRating').click(
	  function(e){
	    var t = $(this);
	    if(t.hasClass("far") && t.hasClass("fa-star")){
	      var prev = t.prevAll(".starRating");
	      if(prev.hasClass("fa-star-half-alt")){
	        prev.removeClass("fa-star-half-alt");
	        prev.addClass("fa-star");
	      }
	      prev.removeClass("far");
	      prev.addClass("fas");
	      t.removeClass("far");
	      t.addClass("fas");
	      $("#ratingVal").val(parseInt(t.prop("id"),10)+1);
	    } else if(t.hasClass("fas") && t.hasClass("fa-star")){
	      var next = t.nextAll(".starRating");
	      if(next.hasClass("fas")){
	      next.removeClass("fas");
	      next.addClass("far");
	        if(next.hasClass("fa-star-half-alt")){
	          next.removeClass("fa-star-half-alt");
	          next.addClass("fa-star");
	        }
	      $("#ratingVal").val(parseInt(t.prop("id"),10) + 1);
	      }else{
	        t.removeClass("fa-star");
	        t.addClass("fa-star-half-alt");
	        $("#ratingVal").val(parseInt(t.prop("id"),10)+0.5);
	      }
	    } else if(t.hasClass("fa-star-half-alt") && t.hasClass("fas")){
	      t.removeClass("fa-star-half-alt");
	      t.addClass("fa-star");
	      t.removeClass("fas");
	      t.addClass("far");
	      $("#ratingVal").val(parseInt(t.prop("id"),10));
	    }
	  }
	)
	}
)