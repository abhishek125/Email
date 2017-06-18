$(document).ready(function(){
    $('input[id=main-input]').change(function() {
        var mainValue = $(this).val();
        if(mainValue.localeCompare("")==0){
            document.getElementById("fake-btn").innerHTML = "Choose File";
        }else{
            document.getElementById("fake-btn").innerHTML = mainValue.replace("C:\\fakepath\\", "");
        }
    });    
});

//    =================================== ends here ============================================ //

   