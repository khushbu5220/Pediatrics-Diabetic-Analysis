angular.module('myApp', ['ngSanitize'])
.controller('myController', ['$scope', function($scope) {
    $scope.editorContent = ''; 
    $scope.editText = ''; 

    $scope.save = function() {
        console.log($scope.editorContent);
        $scope.editText=$scope.editorContent;
    };
}])
.directive('ckeditor', function() {
    return {
        require: '?ngModel',
        link: function(scope, element, attrs, ngModel) {
            if (!ngModel) return;
            ClassicEditor
                .create(element[0])
                .then(editor => {
                    
                    editor.model.document.on('change', () => {
                        scope.$apply(() => {
                            var data = editor.getData();
                            ngModel.$setViewValue(data);
                        });
                    });
                })
                .catch(error => {
                    console.error(error);
                });
        }
    };
});