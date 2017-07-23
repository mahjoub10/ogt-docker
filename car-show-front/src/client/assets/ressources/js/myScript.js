function hideshowPanel(hundler, targetComponentId) {
    var coponentToShow = hundler.getAttribute('data-target');
    $('#' + targetComponentId).children().hide();
    $('#' + coponentToShow).fadeIn("slow");
}

/*function hideshowPanelResult(hundler, targetComponentId) {
  $('#adresse').removeClass('has-error');
  if ($('#adresse').find('.tt-input').val() !== "") {
    var coponentToShow = hundler.getAttribute('data-target');
    $('#' + targetComponentId).children().hide();
    $('#' + coponentToShow).fadeIn("slow");
  } else {
    $('#adresse').addClass('has-error');
  }

}*/

/*var substringMatcher = function (strs) {
  return function findMatches(q, cb) {
    var matches, substringRegex;

    // an array that will be populated with substring matches
    matches = [];

    // regex used to determine if a string contains the substring `q`
    substrRegex = new RegExp(q, 'i');

    // iterate through the pool of strings and for any string that
    // contains the substring `q`, add it to the `matches` array
    $.each(strs, function (i, str) {
      if (substrRegex.test(str)) {
        matches.push(str);
      }
    });

    cb(matches);
  };
};*/


/*var medecin = ['Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California',
  'Colorado', 'Connecticut', 'Delaware', 'Florida', 'Georgia', 'Hawaii',
  'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana',
  'Maine', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota',
  'Mississippi', 'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire',
  'New Jersey', 'New Mexico', 'New York', 'North Carolina', 'North Dakota',
  'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania', 'Rhode Island',
  'South Carolina', 'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont',
  'Virginia', 'Washington', 'West Virginia', 'Wisconsin', 'Wyoming'
];
var specialite = ['Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California',
  'Colorado', 'Connecticut', 'Delaware', 'Florida', 'Georgia', 'Hawaii',
  'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana',
  'Maine', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota',
  'Mississippi', 'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire',
  'New Jersey', 'New Mexico', 'New York', 'North Carolina', 'North Dakota',
  'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania', 'Rhode Island',
  'South Carolina', 'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont',
  'Virginia', 'Washington', 'West Virginia', 'Wisconsin', 'Wyoming'
];*/
/*var adresse = ['Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California',
  'Colorado', 'Connecticut', 'Delaware', 'Florida', 'Georgia', 'Hawaii',
  'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana',
  'Maine', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota',
  'Mississippi', 'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire',
  'New Jersey', 'New Mexico', 'New York', 'North Carolina', 'North Dakota',
  'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania', 'Rhode Island',
  'South Carolina', 'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont',
  'Virginia', 'Washington', 'West Virginia', 'Wisconsin', 'Wyoming'
];*/


jQuery(document).ready(function($) {

    $("[data-toggle='buttons'] button").on('click', function() {
        $('#' + $(this)[0].getAttribute('data-hide')).hide();
        $('#' + $(this)[0].getAttribute('data-show')).fadeIn("slow");
    })

    /* $('#specialite .typeahead').typeahead({
       hint: true,
       highlight: true,
       minLength: 1
     }, {
         name: 'specialite',
         source: substringMatcher(specialite)
       });*/

    /*$('#adresse .typeahead').typeahead({
      hint: true,
      highlight: true,
      minLength: 1
    }, {
        name: 'adresse',
        source: substringMatcher(adresse)
      });*/

    $.datetimepicker.setLocale('en');
    $('#datetimepicker').datetimepicker({
        value: '',
        format: $("#datetimepicker_format_value").val(),
        step: 15
    });

    //calendar paginator

    /* var UIDatepaginator = function () {
       return {
         init: function () {
           $("#datepaginator_sample_1").datepaginator(), $("#datepaginator_sample_2").datepaginator({
             size: "large"
           }), $("#datepaginator_sample_3").datepaginator({
             size: "small"
           }), $("#datepaginator_sample_4").datepaginator({
             onSelectedDateChanged: function (a, t) {
               //alert("Selected date: " + moment(t).format("Do, MMM YYYY"))
             }
           })
         }
       }
     }();
     jQuery(document).ready(function () {
       UIDatepaginator.init()
     });*/

    //map

    $(function() {
       /* $("#map").googleMap({
            zoom: 10, // Initial zoom level (optional)
            coords: [48.895651, 2.290569], // Map center (optional)
            type: "ROADMAP" // Map type (optional)
        });
        $("#map2").googleMap({
            zoom: 10, // Initial zoom level (optional)
            coords: [48.895651, 2.290569], // Map center (optional)
            type: "ROADMAP" // Map type (optional)
        });*/
    })

})