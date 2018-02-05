function imageUpload(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        var user =  document.getElementById("member.username").value;
        reader.onload = function (ev) {
            $('#image')
                .attr('src', ev.target.result);
            console.log(ev);
            console.log(input.files[0]);
            var data = new FormData();
            data.append("file", input.files[0]);
            console.log(data);
            $.ajax({
                type: "POST",
                url: "/member/"+user+"/profile",
                enctype: 'multipart/form-data',
                processData: false,
                contentType: false,
                data: data
            }).done((data) => {
                console.log("yes");
            }).fail((err) => {
                console.log(err);
            });
        };

        reader.readAsDataURL(input.files[0]);
    }
}


function displayCard(status) {
    console.log(status);
    if (status === "false") {
        document.getElementById("payment-form").style.display = "none";
        document.getElementById("payment.name").value = null;
        document.getElementById("payment.number").value = null;
        document.getElementById("payment.zipCode").value = null;
        document.getElementById("payment.cvv").value = null;
        document.getElementById("payment.expirationMonth").value = null;
        document.getElementById("payment.expirationYear").value = null;
    } else {
        document.getElementById("payment-form").style.display = "inherit";
    }
}