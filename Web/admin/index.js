// ========================Check if user is signed in or not and perform actions accordingly ===========================
firebase.auth().onAuthStateChanged(function(user) {
  if (user) {
    // User is signed in.
    $(".login-cover").hide();
    var dialog=document.querySelector("#loginDialog");
    dialog.close();
    $("#userEmail").html(user.email);
  } else {
    // No user is signed in.
    $(".login-cover").show();
    var dialog=document.querySelector("#loginDialog");
    if(!dialog.showModal){
      dialogPolyfill.registerDialog(dialog);
    }
    // Now dialog acts like a native <dialog>.
    dialog.showModal();
  }
});

//============================================== Login User ============================================================
  $("#loginButton").click(
      function(){
          email=$("#loginEmail").val();
          var password=$("#loginPassword").val();
          if(email!="" && password!=""){
            //   show progress bar
              $("#loginProgress").show();
              $("#loginButton").hide();

              //perform sign in
              firebase.auth().signInWithEmailAndPassword(email,password).catch(function(error) {
                var errorCode = error.code;
                var errorMessage = error.message;

                // Handle Errors here.
                $("#loginError").show().text(errorMessage);

              //hide progress bar
              $("#loginProgress").hide();
              $("#loginButton").show();
              });  
              window.location("reload");            
          }
      }      
  );
//Logout process
$("#signOutButton").click(
    function(){
        firebase.auth().signOut().then(function() {
            // Sign-out successful.
          }).catch(function(error) {
            // An error happened.
            alert(error.message);
          });
    }
);

//=========================================== Home page =========================================================
var ref = firebase.database().ref("web/");
ref.child("hospitals/").on('value',getCount);

function getCount(snapshot){
  var state =0, city = 0, totalBloodUnits = 0, hospitals = 0, ap, an, bp, bn, abp, abn, op, on;
  snapshot.forEach(stateSnap=>{
    state = state+1;
    stateSnap.forEach(citySnap=>{
      city = city+1;
      citySnap.forEach(hospitalSnap=>{
        hospitals = hospitals+1;
        var detail = hospitalSnap.val();
        ap = detail.aPCount;
        an = detail.aNCount;
        bp = detail.bPCount;
        bn = detail.bNCount;
        abp = detail.abPCount;
        abn = detail.abNCount;
        op = detail.oPCount;
        on = detail.oNCount;
        totalBloodUnits = totalBloodUnits + ap + bp + an + bn + abp + abn + op + on;
        console.log(ap+":"+an+":"+bp+":"+bn+":"+abp+":"+abn+":"+op+":"+on);
      });
    });
  });
  $("#stateCount").html(state);
  $("#cityCount").html(city);
  $("#totalBloodUnits").html(totalBloodUnits);
  $("#hospitalCount").html(hospitals);
}

// ------------------------------------ Show State Data ------------------------------------------------
function viewStateData(){
  document.getElementById('view-StateData').style.display="inline";
  document.getElementById('add-new-hospital').style.display="none";
  document.getElementById('home').style.display="none";
  document.getElementById('view-hospitals').style.display="none";
  document.getElementById('add-updates').style.display="none";
  document.getElementById('notifyUsers').style.display="none";
}
var database = firebase.database();
var stateDetail;
var ref = database.ref("web/hospitals/").on("value",function showStateData(snapshot){
  $(document).ready(function(){
    $('#stateData').empty();
  });
  snapshot.forEach(stateSnap=>{
    var totalAP=0, totalAN=0, totalBP=0, totalBN=0, totalABP=0, totalABN=0, totalOP=0, totalON=0, totalAll=0; 
    stateSnap.forEach(citySnap=>{
      citySnap.forEach(hospitalSnap=>{
        hCount = hospitalSnap.val();
        ap = hCount.aPCount;
        an = hCount.aNCount;
        bp = hCount.bPCount;
        bn = hCount.bNCount;
        abp = hCount.abPCount;
        abn = hCount.abNCount;
        op = hCount.oPCount;
        on = hCount.oNCount;
        totalAP += ap; 
        totalAN += an;
        totalBP += bp;
        totalBN += bn;
        totalABP += abp;
        totalABN += abn;
        totalOP += op;
        totalON += on;
      });
    });    
    $(document).ready(function(){        
      $stateRow = '<tr><td>' + stateSnap.key + '</td><td>' + totalOP + '</td><td>' + totalON + '</td><td>' + totalAP+ '</td><td>' + totalAN + '</td><td>' + totalBP + '</td><td>' + totalBN + '</td><td>' + totalABP + '</td><td>' + totalABN + '</td></tr>';
      $('#stateData').append($stateRow);  
    });    
    console.log(stateSnap.key+":"+totalOP);
  });
});

// ------------------------------------ Show City Data ------------------------------------------------
function viewCityData(){
  document.getElementById('view-CityData').style.display="inline";
  document.getElementById('view-StateData').style.display="none";
  document.getElementById('add-new-hospital').style.display="none";
  document.getElementById('home').style.display="none";
  document.getElementById('view-hospitals').style.display="none";
  document.getElementById('add-updates').style.display="none";
  document.getElementById('notifyUsers').style.display="none";
}
var database = firebase.database();
var aPc=0, aNc=0, bPc=0, bNc=0, abPc=0, abNc=0, oPc=0, oNc=0;
var ref = database.ref("web/hospitals/").on("value",function showStateData(hsnapshot){
  $(document).ready(function(){
    $('#cityData').empty();
  });
  hsnapshot.forEach(stateSnapshot=>{ 
    stateSnapshot.forEach(citySnapshot=>{
      citySnapshot.forEach(hospitalSnapshot=>{
        hCount = hospitalSnapshot.val();
        ap = hCount.aPCount;
        an = hCount.aNCount;
        bp = hCount.bPCount;
        bn = hCount.bNCount;
        abp = hCount.abPCount;
        abn = hCount.abNCount;
        op = hCount.oPCount;
        on = hCount.oNCount;
        aPc += ap; 
        aNc += an;
        bPc += bp;
        bNc += bn;
        abPc += abp;
        abNc += abn;
        oPc += op;
        oNc += on;
      });
    $(document).ready(function(){        
      $cityRow = '<tr><td>' + citySnapshot.key + '</td><td>' + oPc + '</td><td>' + oNc + '</td><td>' + aPc + '</td><td>' + aNc + '</td><td>' + bPc + '</td><td>' + bNc + '</td><td>' + abPc + '</td><td>' + abNc + '</td></tr>';
      $('#cityData').append($cityRow); 

      console.log(citySnapshot.key+":"+oPc);
    }); 
    });    
    
  });
});

// ------------------------------------ Show Blood Data ------------------------------------------------
function viewBloodData(){
  document.getElementById('view-BloodData').style.display="inline";
  document.getElementById('view-StateData').style.display="none";
  document.getElementById('view-CityData').style.display="none";
  document.getElementById('view-StateData').style.display="none";
  document.getElementById('add-new-hospital').style.display="none";
  document.getElementById('home').style.display="none";
  document.getElementById('view-hospitals').style.display="none";
  document.getElementById('add-updates').style.display="none";
  document.getElementById('notifyUsers').style.display="none";
}
var database = firebase.database();
var aPc=0, aNc=0, bPc=0, bNc=0, abPc=0, abNc=0, oPc=0, oNc=0;
var ref = database.ref("web/hospitals/").on("value",function showStateData(hsnapshot){
  $(document).ready(function(){
    $('#bloodData').empty();
  });
  hsnapshot.forEach(stateSnapshot=>{ 
    stateSnapshot.forEach(citySnapshot=>{
      citySnapshot.forEach(hospitalSnapshot=>{
        hCount = hospitalSnapshot.val();
        ap = hCount.aPCount;
        an = hCount.aNCount;
        bp = hCount.bPCount;
        bn = hCount.bNCount;
        abp = hCount.abPCount;
        abn = hCount.abNCount;
        op = hCount.oPCount;
        on = hCount.oNCount;
        aPc += ap; 
        aNc += an;
        bPc += bp;
        bNc += bn;
        abPc += abp;
        abNc += abn;
        oPc += op;
        oNc += on;
      });
    });
   });  
  // aPc /=2;
  // aNc /= 2;
  // bPc /=2;
  // bNc /=2;
  // abPc /=2;
  // abNc /=2;
  // oPc /=2;
  // oNc /=2;
  $(document).ready(function(){        
    $bloodRow = '<tr><td>' + oPc + '</td><td>' + oNc + '</td><td>' + aPc + '</td><td>' + aNc + '</td><td>' + bPc + '</td><td>' + bNc + '</td><td>' + abPc + '</td><td>' + abNc + '</td></tr>';
    $('#bloodData').append($bloodRow); 

    console.log(oPc+":"+oNc);
  }); 
});



//------------------------------------Adding a new hospital or blood bank--------------------------------------
function showNewHospital(){
  document.getElementById('add-new-hospital').style.display="inline-block";
  document.getElementById('home').style.display="none";
  document.getElementById('view-hospitals').style.display="none";
  document.getElementById('add-updates').style.display="none";
  document.getElementById('notifyUsers').style.display="none";
}
$("#signUpButton").click(
  function(){
    var name = $("#signUpName").val();
    var email = $("#signUpEmail").val();
    var password = $("#signUpPassword").val();
    var address = $("#signUpAddress").val();
    var city = $("#signUpCity").val();
    var state = $("#signUpState").val();
    var pincode = $("#signUpPincode").val();
    var phoneNo = $("#signUpPhone").val();
    var database = firebase.database();
    if(name!="" && email!="" && password!="" && address!="" && city!="" && state!="" && pincode!="" && phoneNo!="")
    {
      //show progress bar
      $("#signUpProgress").show();
      $("#signUpButton").hide();
      // create new hospital
      firebase.auth().createUserWithEmailAndPassword(email, password).then(function (user){
        $("#signUpSuccessDialog").show();
        $("#okButton").click(function(){
          $("#signUpSuccessDialog").hide();
          
        });
          var val = Math.floor(1000 + Math.random() * 1000);
          var id = "HID"+ val; 
          firebase.database().ref('web/hospitals/'+state+'/'+city+'/'+id).set({
            hid:id,
            name: name,
            email: email,
            address:address,
            city:city,
            state:state,
            pincode:pincode,
            phone:phoneNo,
            longitude:"",
            latitude:"",
            photoURL:"",
            redirectURL:"",
            aPCount:"",
            aNCount:"",
            bPCount:"",
            bNCount:"",
            abPCount:"",
            abNCount:"",
            oPCount:"",
            oNCount:"",
          });
          location.reload();
      });      
      //hide progress bar
      $("#signUpProgress").hide();
      $("#signUpButton").show();
    }    
  }
);


// //-------------------------------Synchronize IoT, web and Android for blood unit count-------------------
// var ref = firebase.database().ref("iot/");
// ref.child("hospitals/").on('value',gotUserData);

// function gotUserData (snapshot){
//   var state, city, hid , bloodGroup , cardUID, apc=0, anc=0, bpc=0, bnc=0, abpc=0, abnc=0, opc=0, onc=0;  //out of the loop declaration for further usage
//   snapshot.forEach(stateSnapshot=>{
//     state = stateSnapshot.key; 
//   stateSnapshot.forEach(citySnapshot=>{
//     city = citySnapshot.key;
//     citySnapshot.forEach(hospitalSnapshot=>{
//       hid = hospitalSnapshot.key;
//       console.log(state+":"+city+":"+hid+":");
//       hospitalSnapshot.forEach(childSnapshot=>{
//         var detail = childSnapshot.val();
//         bloodGroup = detail.bloodGroup;
//         cardUID = detail.cardUID;
//         console.log(cardUID+":"+bloodGroup);
//         switch(bloodGroup){
//           case "A+" : apc = apc+1;break;
//           case "A-" : anc = anc+1;break;
//           case "B+" : bpc = bpc+1;break;
//           case "B-" : bnc = bnc+1;break;
//           case "AB+" : abpc = abpc+1;break;
//           case "AB-" : abnc = abnc+1;break;
//           case "O+" : opc = opc+1;break;
//           case "O-" : onc = onc+1;break;
//         };
//         console.log(apc+":"+anc+":"+bpc+":"+bnc+":"+abpc+":"+abnc+":"+opc+":"+onc);
//       });
      
//       firebase.database().ref("web/hospitals/").child(state).child(city).child(hid).once('value',webSnapshot=>{
//         var detailsHospital = webSnapshot.val();
//         webSnapshot.ref.update({
//           aPCount : String(apc),
//           aNCount : String(anc),
//           bPCount : String(bpc),
//           bNCount : String(bnc),
//           abPCount : String(abpc),
//           abNCount : String(abnc),
//           oPCount : String(opc),
//           oNCount : String(onc)
//         });
//         var ap = detailsHospital.aPCount , an = detailsHospital.aNCount, bp = detailsHospital.bPCount, 
//         bn = detailsHospital.bNCount , abp = detailsHospital.abPCount, abn = detailsHospital.abNCount,
//         op = detailsHospital.oPCount, on = detailsHospital.oNCount ;
//         console.log(ap+":"+an+":"+bp+":"+bn+":"+abp+":"+abn+":"+op+":"+on);
//       });
//     });
//   });
// });
// }


//================================= Viewing all hospitals=================================
var hospitalRow;
function viewHospitals(){
  document.getElementById('view-hospitals').style.display="inline-block";
  document.getElementById('home').style.display="none";
  document.getElementById('add-new-hospital').style.display="none";
  document.getElementById('add-updates').style.display="none";
  document.getElementById('notifyUsers').style.display="none";
}
var database = firebase.database();
var ref = database.ref("web/hospitals/").on("value",function showHospitals(snapshot){
  $(document).ready(function(){
    $('#userInfo').empty();
  });
  snapshot.forEach(stateSnap=>{
    stateSnap.forEach(citySnap=>{
    citySnap.forEach(hospitalSnap=>{
      var hid = hospitalSnap.key;
      var name = hospitalSnap.child("name").val();
      var city = hospitalSnap.child("city").val();
      var oPCount = hospitalSnap.child("oPCount").val();
      var oNCount = hospitalSnap.child("oNCount").val();
      var aPCount = hospitalSnap.child("aPCount").val();
      var aNCount = hospitalSnap.child("aNCount").val();
      var bPCount = hospitalSnap.child("bPCount").val();
      var bNCount = hospitalSnap.child("bNCount").val();
      var abPCount = hospitalSnap.child("abPCount").val();
      var abNCount = hospitalSnap.child("abNCount").val();
      
      $(document).ready(function(){
        
        var $userRow = '<tr><td>' + hid + '</td><td>' + name + '</td><td>' + city +'</td><td>' + oPCount + '</td><td>' + oNCount + '</td><td>' + aPCount + '</td><td>' + aNCount + '</td><td>' + bPCount + '</td><td>' + bNCount + '</td><td>' + abPCount + '</td><td>' + abNCount + '</td></tr>';
        $('#userInfo').append($userRow);  
      });
      
      });
    });
  });
});

//==================================== Add new event details as per the user's location ================================
//Notify users
function notifyUsers(){
  document.getElementById('notifyUsers').style.display="inline-block";
  document.getElementById('home').style.display="none";
  document.getElementById('add-new-hospital').style.display="none";
  document.getElementById('view-hospitals').style.display="none";
  document.getElementById('add-updates').style.display="none";
}
//Push the data to firebase
$("#notifyButton").click(function(){
  if(eName!="" && eDate!="" && eTime!="" && eVenue!="" && ePincode!="" && eState!="")
  {
    //Get elements
    var eName = $("#eventName").val();
    var eDate = $("#eventDate").val();
    var eTime = $("#eventTime").val();
    var eVenue = $("#eventVenue").val();
    var eState = $("#eventState").val();
    var ePincode = $("#eventPincode").val();
    // show progress bar
    $("#notifyButton").hide();
    $("#notifyUsersProgress").show();
    firebase.database().ref('web/notifyPeople').push({
      name : eName,
      date : eDate,
      time : eTime,
      venue : eVenue,
      state : eState,
      cityPincode : ePincode,
    });
    //insert data to database
    $("#notifyUsersSuccessDialog").show();
    $("#notificationOkButton").click(function(){   
      $("#notifyUsersSuccessDialog").hide();
      location.reload();
    });
    $("#notifyUsersProgress").hide();
    $("#notifyButton").show();
  }
});
// Show all events
var key;
var database = firebase.database();
var ref = database.ref("web/notifyPeople").on("value",function showAllEvents(snapshot){
  snapshot.forEach(eventDetail=>{
    var eventDetails = eventDetail.val();
    var key = eventDetail.key;
    eventName = eventDetails.name;
    time = eventDetails.time;
    date = eventDetails.date;
    venue = eventDetails.venue;  
    $eventRow = '<tr><td>' + eventName + '</td><td>' + date + '</td><td>' + time + '</td><td>' + venue +'</td><td><i class="material-icons"><a href="#editEvent?uid='+key+'" onclick="editEvent('+key+')" style="text-decoration:none;" id="editEvent">edit</a></i><i class="material-icons"><a href="deleteEvent" onclick="deleteEvent()" style="text-decoration:none;" id="deleteEvent">delete</a></i></tr>';
    $("#viewAllEvents").append($eventRow);
  });
});

function getParameterByName(name, url) {
  if (!url) url = window.location.href;
  name = name.replace(/[\[\]]/g, '\\$&');
  var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
      results = regex.exec(url);
  if (!results) return null;
  if (!results[2]) return '';
  return decodeURIComponent(results[2].replace(/\+/g, ' '));
}

function editEvent(k){
  var firebaseKey = k;
  var database = firebase.database();
  var ref = database.ref("web/notifyPeople/").child(firebaseKey).on("value",function showEventDetails(snapshot){
    var eDetail = snapshot.val();
    console.log(eDetail.name);
  });
}



//=================================================== Add updated newsfeed ==============================================
function addUpdates(){
  document.getElementById('add-updates').style.display="inline-block";
  document.getElementById('home').style.display="none";
  document.getElementById('view-hospitals').style.display="none";
  document.getElementById('add-new-hospital').style.display="none";
  document.getElementById('notifyUsers').style.display="none";
}
// Get Elements
var imageUpload = document.getElementById('newsImage');
var file,storageRef,task,imageURL;
// Listen for when file has been selected
imageUpload.addEventListener('change',function(e){
  //Get file
  file = e.target.files[0]; 
  //Create storage reference
  storageRef = firebase.storage().ref('newsfeed/'+file.name);
  //Upload file
  task = storageRef.put(file);
  //Get file URL
  storageRef.getDownloadURL().then(function(url){
    imageURL = url;
  });
});
$("#newsUpdateButton").click(function()
{
  var newsHeading = $("#newsHeading").val();
  var newsDate = new Date();
  var timeStamp = newsDate.getDate() + "/" + (newsDate.getMonth()+1) + "/" + newsDate.getFullYear() + "  " + newsDate.getHours() + ":" + newsDate.getMinutes() + ":" + newsDate.getSeconds();
  var newsDescription = $("#newsDescription").val();
  var image = $("#newsImage");
  if(newsHeading!="" && newsDescription!="" && image!="")
  {  
    // show progress bar
    $("#newsUpdateButton").hide();
    $("#newsUpdateProgress").show();
    firebase.database().ref('web/newsfeed').push({
      head : newsHeading,
      date : timeStamp,
      description: newsDescription,
      image : imageURL,
    });
    //insert data to database
    $("#newsUpdateSuccessDialog").show();
    $("#okButtonNewsfeed").click(function(){   
    $("#newsUpdateSuccessDialog").hide();
    location.reload();
  });
  $("#newsUpdateProgress").hide();
  $("#newsUpdateButton").show();
  }
});

//show all newsfeed
var key;
var database = firebase.database();
var ref = database.ref("web/newsfeed/").on("value",function showAllEvents(snapshot){
  snapshot.forEach(newsDetail=>{
    var newsDetails = newsDetail.val();
    key = newsDetail.key;
    head = newsDetails.head;  
    $newsRow = '<tr><td>' + head + '</td><td><i class="material-icons"><a href="#editEvent?key='+key+'" onclick="editEvent()" style="text-decoration:none;" id="editEvent">edit</a></i><i class="material-icons"><a href="deleteEvent" onclick="deleteEvent()" style="text-decoration:none;" id="deleteEvent">delete</a></i></tr>';
    $("#viewAllNews").append($newsRow);
    
  });
});