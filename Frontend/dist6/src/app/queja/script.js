
angular.module('DynamicLocaleExample', ['tmh.dynamicLocale', 'ui.bootstrap'])
  .config(function(tmhDynamicLocaleProvider) {
    tmhDynamicLocaleProvider.localeLocationPattern('https://code.angularjs.org/1.2.20/i18n/angular-locale_{{locale}}.js');
  })
  .controller('ExampleCtrl', function($rootScope, $filter, $timeout, tmhDynamicLocale, $locale, $scope, $element) {
    $rootScope.model = {selectedLocale: 'es'};
    $rootScope.changeLocale = tmhDynamicLocale.set('es');
    $rootScope.availableLocales = {
      'en': 'English',
      'de': 'German',
      'fr': 'French',
      'ar': 'Arabic',
      'ja': 'Japanese',
      'ko': 'Korean',
      'zh': 'Chinese',
      'es': 'Spanish'};
    $rootScope.$locale = $locale;
    $rootScope.changeLocale = tmhDynamicLocale.set;
    
  });
    