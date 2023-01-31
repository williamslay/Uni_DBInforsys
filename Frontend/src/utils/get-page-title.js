import defaultSettings from '@/settings'

const title = defaultSettings.title || '南京邮电大学信息推送管理系统'

export default function getPageTitle(pageTitle) {
  if (pageTitle) {
    return `${pageTitle} - ${title}`
  }
  return `${title}`
}
