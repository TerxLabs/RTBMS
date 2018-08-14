
var handles = ["SELECT STATE","Andaman and Nicobar Islands","Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chandigarh","Chhattisgarh","Dadra and Nagar Haveli","Daman and Diu","Delhi","Goa","Gujarat","Haryana","Himachal Pradesh","Jammu and Kashmir","Jharkhand","Karnataka","Kerala","Lakshadweep","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Odisha","Puducherry","Punjab", "Rajasthan","Sikkim","Tamil Nadu","Telangana","Tripura","Uttar Pradesh","Uttarakhand","West Bengal"];

$(function() {
  var options = '';
  for (var i = 0; i < handles.length; i++) {
      options += '<option value="' + handles[i] + '">' + handles[i] + '</option>';
  }
  $('#signUpState').html(options);
});
function selct_district($val)
{
    if($val=='SELECT STATE') {
   var options = '';
  $('#signUpCity').html(options);
  }

 if($val=='Andaman and Nicobar Islands'){
     var andaman = ["Nicobar", "North and Middle Andaman","South Andaman"];
     $(function(){
         var options ='';
         for(var i=0;i<andaman.length;i++){
             options += '<option value = "' +andaman[i] + '">' + andaman[i] + '</option>';
         } 
         $('#signUpCity').html(options);
     });
 }

 if($val=='Andhra Pradesh') {
   var andhra = ["Anantapur","Chittoor","East Godavari","Guntur","Krishna","Kurnool","Prakasam","Srikakulam","Sri Potti Sri Ramulu Nellore","Vishakhapatnam","Vizianagaram","West Godavari","Cudappah"];
   $(function() {
  var options = '';
  for (var i = 0; i < andhra.length; i++) {
      options += '<option value="' + andhra[i] + '">' + andhra[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }
  
  if ($val=='Arunachal Pradesh') {
    var ap = ["Anjaw","Changlang","Dibang Valley","East Siang","East Kameng","Kamle","Kurung Kumey","Kra Daadi","Lohit","Longding","Lower Dibang Valley","Lower Siang","Lower Subansiri","Namsai","Papum Pare","Siang","Tawang","Tirap","Upper Dibang Valley","Upper Siang","Upper Subansiri","West Kameng","West Siang"];
   $(function() {
  var options = '';
  for (var i = 0; i < ap.length; i++) {
      options += '<option value="' + ap[i] + '">' + ap[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }
  
  if ($val=='Assam') {
    var assam = ["Baksa","Barpeta","Biswanath","Bongaigaon","Cachar","Charaideo","Chirang","Darrang","Dhemaji","Dima Hasao","Dhubri","Dibrugarh","Goalpara","Golaghat","Hailakandi","Hojai","Jorhat","Kamrup","Kamrup Metropolitan","Karbi Anglong","Karimganj","Kokrajhar","Lakhimpur","Majuli","Morigaon","Nagaon","Nalbari","Sivasagar","Sonitpur","South Salmara-Mankachar","Tinsukia","Udalguri","West Karbi Anglong"];
   $(function() {
  var options = '';
  for (var i = 0; i < assam.length; i++) {
      options += '<option value="' + assam[i] + '">' + assam[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }
  
  if ($val=='Bihar') {
    var bihar = ["Araria","Arwal","Aurangabad","Banka","Begusarai","Bhagalpur","Bhojpur","Buxar","Darbhanga","East Champaran","Gaya","Gopalganj","Jamui","Jehanabad","Kaimur","Katihar","Khagaria","Kishanganj","Kaimur","Katihar","Lakhisarai","Madhepura","Madhubani","Munger","Muzaffarpur","Nalanda","Nawada","Patna","Purnia","Rohtas","Saharsa","Samastipur","Saran","Sheikhpura","Sheohar","Sitamarhi","Siwan","Supaul","Vaishali","West Champaran"];
   $(function() {
  var options = '';
  for (var i = 0; i < bihar.length; i++) {
      options += '<option value="' + bihar[i] + '">' + bihar[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }

  if($val == 'Chandigarh'){
      var Chandigarh = ["Chandigarh"];
      $(function(){
        var options = '';
        for(var i=0;i<Chandigarh.length;i++){
            options += '<option value="' + Chandigarh[i] + '">' + Chandigarh[i] + '<option>';
        }
        $('#signUpCity').html(options);
      });
  }
  
  if ($val=='Chhattisgarh') {
    var Chhattisgarh = ["Balod","Baloda Bazar","Balrampur","Bastar","Bemetara","Bijapur","Bilaspur","Dantewada","Dhamtari","Durg","Gariaband","Jashpur","Janjgir-Champa","Korba","Kondagaon","Koriya","Kanker","Kabirdham (formerly Kawardha)","Mahasamund","Mungeli","Narayanpur","Raigarh","Raipur","Rajnandgaon","Raipur","Sukma","Surajpur","Surguja"];
   $(function() {
        var options = '';
        for (var i = 0; i < Chhattisgarh.length; i++) {
            options += '<option value="' + Chhattisgarh[i] + '">' + Chhattisgarh[i] + '</option>';
        }
        $('#signUpCity').html(options);
    });
  }
  
  if ($val=='Dadra and Nagar Haveli') {
    var dadra = ["Amal","Silvassa","Dadra and Nagar Haveli"];
   $(function() {
  var options = '';
  for (var i = 0; i < dadra.length; i++) {
      options += '<option value="' + dadra[i] + '">' + dadra[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }
  
  if ($val=='Daman and Diu') {
    var daman = ["Daman","Diu"];
   $(function() {
  var options = '';
  for (var i = 0; i < daman.length; i++) {
      options += '<option value="' + daman[i] + '">' + daman[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }
  
  if ($val=='Delhi') {
    var delhi = ["Central Delhi","Delhi","East Delhi","New Delhi","North Delhi","North East Delhi","North West Delhi","Noida","West Delhi","Patparganj","Shahdara","Sonabarsa","South West Delhi","South Delhi","South East Delhi","Tughlakabad"];
   $(function() {
  var options = '';
  for (var i = 0; i < delhi.length; i++) {
      options += '<option value="' + delhi[i] + '">' + delhi[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }
  
  if ($val=='Goa') {
    var goa = ["Bardez","Bicholim","Canacona","Chapora","Dabolim","Madgaon","Marmugao (Marmagao)","Panaji Port","Panjim","Pellet Plant Jetty/Shiroda","Pernem","Ponda","Quepem","Sanguem","Satari","Salcete","Talpona","Tiswadi","Vasco da Gama"];
   $(function() {
  var options = '';
  for (var i = 0; i < goa.length; i++) {
      options += '<option value="' + goa[i] + '">' + goa[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }
  
  if ($val=='Gujarat') {
    var gujarat = ["Ahmedabad","Amreli","Anand","Aravalli","Banaskantha","Bharuch","Bhavnagar","Botad","Chhota Udaipur","Dahod","Dang","Devbhoomi Dwarka","Gandhinagar","gir Somnath","Jamnagar","Junagadh","Kutch","Kheda","Mahisagar","Mehsana","Morbi","Narmada","Navsari","Patan","Panchmahal","Porbandar","Rajkot","Sabarkantha","Surendranagar","Surat","Tapi","Vadodara","Valsad"];
   $(function() {
  var options = '';
  for (var i = 0; i < gujarat.length; i++) {
      options += '<option value="' + gujarat[i] + '">' + gujarat[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }
  
  if ($val=='Haryana') {
    var haryana = ["Ambala","Bhiwani","Charkhi Dadri","Faridabad","Fatehabad","Gurugram","Hisar","Jhajjar","Jind","Karnal","Kaithal","Kurukshetra","Mahendragarh","Mewat","Nuh","Palwal","Panchkula","Panipat","Rewari","Rohtak","Sirsa","Sonipat","Yamunanagar"];
   $(function() {
  var options = '';
  for (var i = 0; i < haryana.length; i++) {
      options += '<option value="' + haryana[i] + '">' + haryana[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }
  
  
  if ($val=='Himachal Pradesh') {
    var himachal = ["Baddi","Baitalpur","Bilaspur","Chamba","Dharamsala","Hamirpur","Kangra","Kinnaur","Kullu","Lahaul & Spiti","Mandi","Simla","Sirmaur","Solan","Una"];
   $(function() {
  var options = '';
  for (var i = 0; i < himachal.length; i++) {
      options += '<option value="' + himachal[i] + '">' + himachal[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }
  
  if ($val=='Jammu and Kashmir') {
    var jammu = ["Anantnag","Bandipora","Baramulla","Budgam","Doda","Ganderbal","Jammu","Kargil","Kashmir","Kathua","Kishtwar","Kulgam","Kupwara","Ladakh","Leh","Pulwama","Poonch","Rajouri","Ramban","Reasi","Samba","Shopian","Srinagar","Udhampur"];
   $(function() {
  var options = '';
  for (var i = 0; i < jammu.length; i++) {
      options += '<option value="' + jammu[i] + '">' + jammu[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }
  
  if ($val=='Jharkhand') {
    var jharkhand = ["Bokaro","Chatra","Deoghar","Dhanbad","Dumka","East Singhbhum","Garhwa","Giridih","Godda","Gumla","Hazaribag","Jamtara","Khunti","Koderma","Latehar","Lohardaga","Pakur","Palamu","Ramgarh","Ranchi","Sahebganj","Saraikela Kharsawan","Simdega","West Singhbhum"];
   $(function() {
  var options = '';
  for (var i = 0; i < jharkhand.length; i++) {
      options += '<option value="' + jharkhand[i] + '">' + jharkhand[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }
  
  if ($val=='Karnataka') {
    var karnataka = ["Bagalkot","Bengaluru Rural","Bengaluru Urban","Belagavi","Ballari","Bidar","Bijapur","Chamarajanagar", "Chikkamagaluru","Chikballapur","Chitradurga","Davanagere","Dharwad","Dakshina Kannada","Gadag","Gulbarga","Hassan","Haveri","Kalaburagi","Kodagu","Kolar","Koppal","Mandya","Mysuru","Raichur","Ramanagara","Shivamogga","Tumakuru","Udupi","Uttara Kannada","Vijayapur","Yadgir"];
   $(function() {
  var options = '';
  for (var i = 0; i < karnataka.length; i++) {
      options += '<option value="' + karnataka[i] + '">' + karnataka[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }
  
  if ($val=='Kerala') {
    var kerala = ["Alappuzha","Ernakulam","Idukki","Kannur","Kasaragod","Kollam","Kottayam","Kozhikode","Malappuram","Palakkad","Pathanamthitta","Thrissur","Thiruvananthapuram","Wayanad"];
   $(function() {
  var options = '';
  for (var i = 0; i < kerala.length; i++) {
      options += '<option value="' + kerala[i] + '">' + kerala[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }

  if($val == 'Lakshadweep'){
      var Lakshadweep = ["Agatti","Amini","Andrott","Bitra","Chetlat","Kadmat","Kalpeni","Kavaratti","Kiltan","Minicoy"];
      $(function(){
        var options = '';
        for(var i=0;i<Lakshadweep.length;i++){
            options += '<option value="' + Lakshadweep[i] + '">' + Lakshadweep[i] + '</option>';
        }
        $('#signUpCity').html(options);
      });
  }
  
  if ($val=='Madhya Pradesh') {
    var mp = ["Agar Malwa","Alirajpur","Anuppur","Ashoknagar","Balaghat","Barwani","Betul","Bhilai","Bhind","Bhopal","Burhanpur","Chhatarpur","Chhindwara","Damoh","Datia","Dewas","Dhar","Dindori","Guna","Gwalior","Harda","Hoshangabad","Indore","Itarsi","Jabalpur","Jhabua","Katni","Khajuraho","Khandwa","Khargone","Malanpur","Malanpuri (Gwalior)","Mandla","Mandsaur","Morena","Narsinghpur","Neemuch","Panna","Pithampur","Raipur","Raisen","Rajgarh","Ratlam","Rewa","Sagar","Satna","Sehore","Seoni","Shahdol","Shahjapur","Sheopur","Shivpuri","Sidhi","Singrauli","Tikamgarh","Ujjain","Umaria","Vidisha"];
   $(function() {
  var options = '';
  for (var i = 0; i < mp.length; i++) {
      options += '<option value="' + mp[i] + '">' + mp[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }
  
  if ($val=='Maharashtra') {
    var maharashtra = ["Ahmednagar","Akola","Alibag","Amaravati","Arnala","Aurangabad","Bandra","Bassain","Beed","Belapur","Bhandara","Bhiwandi","Bhusaval","Borliai-Mandla","Buldhana","Chandrapur","Dahanu","Daulatabad","Dhule","Dighi (Pune)","Dombivali","Gadchiroli","Goa","Gondia","Hingoli","Jaitapur","Jalgaon","Jalna","Jawaharlal Nehru (Nhava Sheva)","Kalyan","Karanja","Kelwa","Khopoli","Kolhapur","Latur","Lonavale","Malegaon","Malwan","Manori","Mira Bhayandar","Miraj","Mumbai","Mumbai(Suburban)","Murad","Nagpur","Nalasopara","Nanded","Nandurbar","Nandgaon","Nasik","Navi Mumbai","Nhave","Osmanabad","Palghar","Panvel","Parbhani","Pimpri","Pune","Raigad","Ratnagiri","Sangli","Satara","Sholapur","Shrirampur","Shriwardhan","Sindhudurg","Solapur","Tarapur","Thana","Thane","Trombay","Varsova","Vengurla","Virar","Wardha","Washim","Yavatmal"];
   $(function() {
  var options = '';
  for (var i = 0; i < maharashtra.length; i++) {
      options += '<option value="' + maharashtra[i] + '">' + maharashtra[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }
  
   if ($val=='Manipur') {
    var manipur = ["Bishnupur","Churachandpur","Chandel","Imphal East","Jiribam","Kangpokpi","Kakching","Kamjong","Noney","Pherzawl","Senapati","Tamenglong","Tengnoupal","Thoubal","Ukhrul","Imphal West"];
   $(function() {
  var options = '';
  for (var i = 0; i < manipur.length; i++) {
      options += '<option value="' + manipur[i] + '">' + manipur[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }
  
   if ($val=='Meghalaya') {
    var meghalaya = ["Ampati","Baghamara","Balet","Barsora","Bolanganj","Dalu","Dawki","Ghasuapara","Jowai","Khliehriat","Mahendraganj","Mawkyrwat","Moreh","Nongstoin","Ri Bhoi","Resubelpura","Ryngku","Shella Bazar","Shillong","Tura","Williamnagar"];
   $(function() {
  var options = '';
  for (var i = 0; i < meghalaya.length; i++) {
      options += '<option value="' + meghalaya[i] + '">' + meghalaya[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }
  
   if ($val=='Mizoram') {
    var mizoram = ["Aizawl","Champai","Kolasib","Lawngtlai","Lunglei","Mamit","Saiha","Serchhip"];
   $(function() {
  var options = '';
  for (var i = 0; i < mizoram.length; i++) {
      options += '<option value="' + mizoram[i] + '">' + mizoram[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }
  
   if ($val=='Nagaland') {
    var nagaland = ["Dimapur","Kiphire","Kohima","Longleng","Mokokchung","Mon","Noklak","Peren","Phek","Tuensang","Wokha","Zunheboto"];
   $(function() {
  var options = '';
  for (var i = 0; i < nagaland.length; i++) {
      options += '<option value="' + nagaland[i] + '">' + nagaland[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }
  
  if ($val=='Odisha') {
    var orissa = ["Angul","Boudh","Balangir","Bargarh","Balasore","Bhadrak","Cuttack","Debagarh","Dhenkanal","Ganjam","Gajapati","Jharsuguda","Jajpur","Jagatsinghapur","Khordha","Kendujhar","Kalahandi","Kandhamal","Koraput","Kendrapara","Malkangiri","Mayurbhanj","Nabarangpur","Nuapada","Nayagarh","Puri","Rayagada","Sambalpur","Subarnapur","Sundergarh"];
   $(function() {
  var options = '';
  for (var i = 0; i < orissa.length; i++) {
      options += '<option value="' + orissa[i] + '">' + orissa[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }
  
  if ($val=='Puducherry') {
    var puducherry = ["Karaikal","Mahe","Pondicherry","Yanam"];
   $(function() {
  var options = '';
  for (var i = 0; i < puducherry.length; i++) {
      options += '<option value="' + puducherry[i] + '">' + puducherry[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }
  
  if ($val=='Punjab') {
    var punjab = ["Amritsar","Barnala","Bathinda","Firozpur","Faridkot","Fatehgarh Sahib","Fazilka","Gurdaspur","Hoshiarpur","Jalandhar","Kapurthala","Ludhiana","Mansa","Moga","Sri Muktsar Sahib","Pathankot", "Patiala","Rupnagar","Sahibzada Ajit Singh Nagar","Sangrur","Shahid Bhagat Singh Nagar","Taran Taran"];
   $(function() {
  var options = '';
  for (var i = 0; i < punjab.length; i++) {
      options += '<option value="' + punjab[i] + '">' + punjab[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }
  
  if ($val=='Rajasthan') {
    var rajasthan = ["Ajmer","Alwar","Banswara","Baran","Barmer","Bharatpur","Bhilwara","Bikaner","Bundi","Chittorgarh","Churu","Dausa","Dholpur","Dungarpur","Hanumangarh","Jaipur","Jaisalmer","Jalor","Jhalawar","Jhunjhunu","Jodhpur","Karauli","Kardhan","Kota","Munabao Rail Station","Nagaur","Pali","Rajsamand","Sawai Madhopur","Sikar","Sirohi","Sri Ganganagar","Shahdol","Shimoga","Tonk","Udaipur"];
   $(function() {
  var options = '';
  for (var i = 0; i < rajasthan.length; i++) {
      options += '<option value="' + rajasthan[i] + '">' + rajasthan[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }
  
  if ($val=='Sikkim') {
    var sikkim = ["Chamurci","Chungthang","Gangtok","Geyzing","Gyalshing","Mangan","Namchi","Pakyong","Ravong","Rongli","Soreng"];
   $(function() {
  var options = '';
  for (var i = 0; i < sikkim.length; i++) {
      options += '<option value="' + sikkim[i] + '">' + sikkim[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }
  
  
  if ($val=='Tamil Nadu') {
    var tn = ["Ariyalur","Chennai","Coimbatore","Cuddalore","Dharmapuri","Dindigul","Erode","Kanchipuram","Kanyakumari","Karur","Krishnagiri","Madurai","Mandapam","Nagapattinam","Nilgiris","Namakkal","Perambalur","Pudukkottai","Ramanathapuram","Salem","Sivaganga","Thanjavur","Thiruvallur","Tiruppur","Tiruchirappalli","Theni","Tirunelveli","Thanjavur","Thoothukudi","Tiruvarur","Tiruvallur","Tiruvannamalai","Vellore","Villupuram","Virdhunagar"];
   $(function() {
  var options = '';
  for (var i = 0; i < tn.length; i++) {
      options += '<option value="' + tn[i] + '">' + tn[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }
  
  
  if ($val=='Telangana') {
    var telangana = ["Adilabad","Bhadradri Kothagudem","Hyderabad","Jagtial","Jangaon","Jayashankar Bhupalapally","Jogulamba Gadwal","Kamareddy","Karimnagar","Khammam","Kumarambheem Asifabad","Mahbubabad","Mahabubnagar","Mancherial","Medak","Medchal","Nagarkurnool","Nalgonda","Nirmal","Nizamabad","Pedapalli","Rajanna Sircilla","Ranga Reddy","Sangareddy","Siddipet","Suryapet","Vikarabad","Wanaparthy","Warangal","Yadadri Bhuvanagiri"];
   $(function() {
  var options = '';
  for (var i = 0; i < telangana.length; i++) {
      options += '<option value="' + telangana[i] + '">' + telangana[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }
  
  
  if ($val=='Tripura') {
    var tripura = ["Agartala","Dhalai","Gomati","Kailashahar","Kamalpur","Kanchanpur","Kel Sahar Subdivision","Khowai","Khowaighat","Mahurighat","Old Raghna Bazar","Sabroom","Srimantapur","Sipahijala","Unakoti"];
   $(function() {
  var options = '';
  for (var i = 0; i < tripura.length; i++) {
      options += '<option value="' + tripura[i] + '">' + tripura[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }
  
  
  if ($val=='Uttar Pradesh') {
    var up = ["Agra","Allahabad","Aligarh","Ambedkar Nagar","Amethi","Amroha","Auraiya","Azamgarh","Bagpat","Bahraich","Ballia","Balrampur","Banbasa","Banda","Barabanki","Bareilly","Basti","Berhni","Bhadohi","Bijnor","Budaun","Bulandshahr","Chandauli","Chitrakoot","Dadri","Deoria","Dharchula","Etah","Etawah","Faizabad","Farrukhabad","Fatehpur","Firozabad","Gandhar","Gauriphanta","Gautam Buddha Nagar","Ghaziabad","Ghazipur","Gonda","Gorakhpur","Gunji","Jalaun","Jhansi","Jarwa","Jaunpur","Jhulaghat (Pithoragarh)","Hapur","Hardoi","Hathras","Hamirpur","Kanpur","Kannauj","Kanpur Dehat","Kanpur Nagar","Kasganj","Katarniyaghat","Kaushambi","Khunwa","Kushinagar","Lakhimpur Kheri","Lalitpur","Loni","Lucknow","Maharajganj","Mahoba","Manipuri","Mathura","Mau","Meerut","Mirzapur","Moradabad","Muzaffarnagar","Nepalgunj Road","Pakwara (Moradabad)","Pantnagar","Pilibhit","Pratapgarh","Raebareli","Rampur","Saharanpur","Sambhal","Sant Kabir Nagar","Sant Ravidas Nagar","Sitapur","Siddarthnagar","Shahjahanpur","Shamli","Shravasti","Sonbhadra","Sonauli","Sultanpur","Surajpur","Tikonia","Varanasi","Unnao"];
   $(function() {
  var options = '';
  for (var i = 0; i < up.length; i++) {
      options += '<option value="' + up[i] + '">' + up[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }
  
  
  if ($val=='Uttarakhand') {
    var uttarakhand = ["Almora","Badrinath","Bageshwar","Bangla","Barkot","Bazpur","Chamoli","Champawat","Chopra","Dehradun","Dwarahat","Garhwal","Haldwani","Hardwar","Haridwar","Jamal","Jwalapur","Kalsi","Kashipur","Mall","Mussoorie","Nahar","Nainital","Pantnagar","Pauri Garhwal","Pithoragarh","Rameshwar","Rishikesh","Rohni","Roorkee","Rudraprayag","Sama","Saur","Tehri Garhwal","Udham Singh Nagar","Uttarakshi"];
   $(function() {
  var options = '';
  for (var i = 0; i < uttarakhand.length; i++) {
      options += '<option value="' + uttarakhand[i] + '">' + uttarakhand[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }
  
  
  if ($val=='West Bengal') {
    var wb = ["Alipurduar","Bankura","Bardhaman","Birbhum","Cooch Behar","Dakshin Dinajpur","Darjeeling","Hooghly","Howrah","Jalpaiguri","Jhargram","Kalimpong","Kolkata","Malda","Murshidabad","Nadia","North 24 Parganas","Paschim Bardhaman","Paschim Medinipur","Purba Bardhaman","Purba Medinipur","Purulia","South 24 Parganas","Uttar Dinajpur"];
   $(function() {
  var options = '';
  for (var i = 0; i < wb.length; i++) {
      options += '<option value="' + wb[i] + '">' + wb[i] + '</option>';
  }
  $('#signUpCity').html(options);
  });
  }
  
}