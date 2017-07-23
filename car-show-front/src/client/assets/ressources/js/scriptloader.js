/*
    SCRIPT LOADER
    Author:  Aymen Boumayza
    Profile: https://www.linkedin.com/in/aymen-boumayza-17b35314/
    Email:   aymen.boumyza@gmail.com
*/


(function($) {

    $.fn.scriptLoad = function(options) {

        var settings = $.extend({
            source: null,
            tag: 'head'
        }, options);

   for(k in settings.source){
      var  ss = settings.source[k]; 
      var t   = document.querySelectorAll('[src="' + ss + '"]');
        if(t.length == 0) 
        {

                var uid = ss.split('/');
                uid = uid[uid.length - 1];
                uid = uid
                    .replace('.min.js', '')
                    .replace('-min.js', '')
                    .replace('.js', '')
                    .replace(/[.-]/g, ' ')                  
                    .toLowerCase()
                    .replace(/(^|\s)[a-z]/g, function(c) {
                        return c.toUpperCase();
                    })
                    .replace(/\s/g, '');
          
            var obj = document.createElement("script");
            obj.setAttribute("id", uid);
            obj.setAttribute("src", ss);
            obj.setAttribute("time", new Date().getTime());
            obj.setAttribute("async", "async");
            obj.setAttribute("type", "text/javascript");
            obj.setAttribute("charset", "utf-8");

            document.getElementsByTagName(settings.tag)[0].appendChild(obj);
        } // end if
        else
        {   
            var temps = t[0].getAttribute('time');
                temps = new Date(parseInt(temps));            

        }

    } // end for boucle
    };

}(jQuery));