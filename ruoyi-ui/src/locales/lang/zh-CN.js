import { locales } from 'ant-design-vue'
const zhCN = locales.zh_CN
const components = {
  antLocale: zhCN,
  momentName: 'zh-cn'
}

const locale = {
  'message': '-',
  'logout': '退出登录',
  'menu.home': '首页',

  'menu.account': '个人页',
  'menu.account.center': '个人中心',
  'menu.account.settings': '个人设置',
  'menu.account.settings.base': '基本设置',
  'menu.account.settings.security': '安全设置',
  'menu.account.settings.custom': '个性化设置',
  'menu.account.settings.binding': '账户绑定',
  'menu.account.settings.notification': '新消息通知',

  'layouts.usermenu.dialog.title': '提示',
  'layouts.usermenu.dialog.content': '你真的要离开了嘛',

  'app.setting.pagestyle': '整体风格设置',
  'app.setting.pagestyle.light': '亮色菜单风格',
  'app.setting.pagestyle.dark': '暗色菜单风格',
  'app.setting.pagestyle.realdark': '暗黑菜单风格',
  'app.setting.themecolor': '主题色',
  'app.setting.navigationmode': '导航模式',
  'app.setting.content-width': '内容区域宽度',
  'app.setting.fixedheader': '固定 Header',
  'app.setting.fixedsidebar': '固定侧边菜单',
  'app.setting.sidemenu': '侧边栏导航',
  'app.setting.topmenu': ' 顶部栏导航',
  'app.setting.content-width.fixed': '固定',
  'app.setting.content-width.fluid': '流式',
  'app.setting.othersettings': '其他设置',
  'app.setting.weakmode': '色弱模式',
  'app.setting.copy': '拷贝设置',
  'app.setting.loading': '主题加载中',
  'app.setting.copyinfo': '复制成功，替换src/config/defaultSettings.js文件中的配置',
  'app.setting.production.hint': '配置栏只在开发环境用于预览，生产环境不会展现，请手动修改配置文件。',

  'app.setting.themecolor.daybreak': '拂晓蓝（默认）',
  'app.setting.themecolor.dust': '薄暮',
  'app.setting.themecolor.volcano': '火山',
  'app.setting.themecolor.sunset': '日暮',
  'app.setting.themecolor.cyan': '明青',
  'app.setting.themecolor.green': '极光绿',
  'app.setting.themecolor.geekblue': '极客蓝',
  'app.setting.themecolor.purple': '酱紫'
}

export default {
  ...components,
  ...locale
}
