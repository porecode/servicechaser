
define(['scripts/lib/jquery/jquery-min.js'],
    function() {
      function get(name, callback) {
        tmlId = 'tml-'+ name;
        if ($('#' + tmlId).length > 0) {
          callback($('#' + tmlId).html());
        } else {
          $('<script/>')
            .attr('id', tmlId)
            .attr('type', 'text/x-mustache')
            .load('scripts/src/template/' + name + '.html', callback)
            .appendTo('body');
        }
      }

      return {
        getTemplate: get
      }
  });

