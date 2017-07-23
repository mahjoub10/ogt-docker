var AppCalendar = function() {

    return {
        //main function to initiate the module
        init: function() {
            this.initCalendar();
        },

        initCalendar: function() {

            if (!jQuery().fullCalendar) {
                return;
            }

          
            var h = {};

            if (App.isRTL()) {
                if ($('#calendar').parents(".portlet").width() <= 720) {
                    $('#calendar').addClass("mobile");
                    h = {
                       left: 'title',
                        center: '',
                        right: 'prev,next,agendaWeek'
                    };
                } else {
                    $('#calendar').removeClass("mobile");
                    h = {
                        left: 'title',
                        center: '',
                        right: 'prev,next,agendaWeek'
                    };
                }
            } else {
                if ($('#calendar').parents(".portlet").width() <= 720) {
                    $('#calendar').addClass("mobile");
                    h = {
                       left: 'title',
                        center: '',
                        right: 'prev,next,agendaWeek'
                        
                       
                    };
                } else {
                    $('#calendar').removeClass("mobile");
                    h = {
                        left: 'title',
                        center: '',
                        right: 'prev,next,agendaWeek'
                    };
                }
            }

          

        

          

            $('#calendar').fullCalendar('destroy'); // destroy the calendar
            $('#calendar').fullCalendar({ //re-initialize the calendar
                lang: 'fr',
                header: h,
                defaultView: 'agendaWeek', // change default view with available options from http://arshaw.com/fullcalendar/docs/views/Available_Views/ 
                timeFormat: 'HH:mm',
               columnFormat: {
            month: 'ddd',
            week: 'dddd MM/YYYY',
            day: 'dddd d/M'
        },
         eventRender: function(event, element, view) {
        return $('<div class="event-label">' + event.title + '</div>');
    },

                slotLabelFormat:"HH:mm",
                slotLabelInterval:'00:15:00',
                slotDuration: '00:15:00',
                editable: false,
                droppable: false, // this allows things to be dropped onto the calendar !!!
                viewRender: function (view, element) {
                    //The title isn't rendered until after this callback, so we need to use a timeout.
                    window.setTimeout(function () {
                        $("#calendar").find('.fc-toolbar > div > h2').empty().append(
                                "<div> Rendez-vous disponibles</div>"
                                );
                    }, 0);
                },
             eventSources: [
                 {
                        events: [
                             {
                                title: '10:00',
                                start: '2016-12-23T10:00:00'
                                
                            },
                            {
                                title: '00:15',
                                start: '2016-12-24T00:15:00'
                                
                            },
                            {
                                title: '01:00',
                                start: '2016-12-24T01:00:00'
                               
                            }
            
                ],
                 color: 'black',     // an option!
            textColor: 'yellow' // an option!
                 }
             ]
                
            });

        }

    };

}();

jQuery(document).ready(function() {    
  // AppCalendar.init(); 
   $('.btn-consult-detail').click(function() {
 // AppCalendar.init(); 
});
   
});

