
define(['scripts/lib/jquery/jquery-min.js'],
    function() {
      function get(name, callback) {
        tmlId = 'tml-'+ name;
        if ($('#' + tmlId).length == 0) {
          $.get('scripts/src/template/' + name + '.mustache',
            function(content) {
              $('<script/>')
                .attr('id', tmlId)
                .attr('type', 'text/x-mustache')
                .html(content)
                .appendTo('body');
              callback(content); 
            }
          );
        } else {
          callback($('#' + tmlId).html());
        }
      }

      return {
        getTemplate: get
      }
  });

