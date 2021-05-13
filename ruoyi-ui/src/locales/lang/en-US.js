import antdEnUS from 'ant-design-vue/es/locale-provider/en_US'
const components = {
  antLocale: antdEnUS,
  momentName: 'eu'
}

const locale = {
  'message': '-',
  'logout': 'Logout',
  'menu.home': 'Home',

  'menu.account': 'Account',
  'menu.account.center': 'Center',
  'menu.account.settings': 'Settings',
  'menu.account.settings.base': 'Base',
  'menu.account.settings.security': 'Security',
  'menu.account.settings.custom': 'Custom',
  'menu.account.settings.binding': 'Binding',
  'menu.account.settings.notification': 'Notification',

  'layouts.usermenu.dialog.title': 'Message',
  'layouts.usermenu.dialog.content': 'Do you really log-out.',

  'app.setting.pagestyle': 'Page style setting',
  'app.setting.pagestyle.light': 'Light style',
  'app.setting.pagestyle.dark': 'Dark style',
  'app.setting.pagestyle.realdark': 'RealDark style',
  'app.setting.themecolor': 'Theme Color',
  'app.setting.navigationmode': 'Navigation Mode',
  'app.setting.content-width': 'Content Width',
  'app.setting.fixedheader': 'Fixed Header',
  'app.setting.fixedsidebar': 'Fixed Sidebar',
  'app.setting.sidemenu': 'Side Menu Layout',
  'app.setting.topmenu': 'Top Menu Layout',
  'app.setting.content-width.fixed': 'Fixed',
  'app.setting.content-width.fluid': 'Fluid',
  'app.setting.othersettings': 'Other Settings',
  'app.setting.weakmode': 'Weak Mode',
  'app.setting.copy': 'Copy Setting',
  'app.setting.loading': 'Loading theme',
  'app.setting.copyinfo': 'copy success，please replace defaultSettings in src/models/setting.js',
  'app.setting.production.hint': 'Setting panel shows in development environment only, please manually modify',

  'app.setting.themecolor.daybreak': 'daybreak',
  'app.setting.themecolor.dust': 'dust',
  'app.setting.themecolor.volcano': 'volcano',
  'app.setting.themecolor.sunset': 'sunset',
  'app.setting.themecolor.cyan': 'cyan',
  'app.setting.themecolor.green': 'green',
  'app.setting.themecolor.geekblue': 'geekblue',
  'app.setting.themecolor.purple': 'purple'

}

export default {
  ...components,
  ...locale
}
