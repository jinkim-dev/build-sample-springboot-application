/**
 * Define all of your application routes here
 * for more information on routes, see the
 * official documentation https://router.vuejs.org/en/
 */
export default [
  {
    path: '/',
    name: 'BuildList',
    view: 'build/BuildList'
  },
  {
    path: '/build/BuildCreate',
    name: 'BuildCreate',
    view: 'build/BuildCreate'
  },
  {
    path: '/build/BuildUpdate',
    name: 'BuildUpdate',
    view: 'build/BuildUpdate'
  },
  {
    path: '/build/buildView',
    name: 'BuildView',
    view: 'build/BuildView'
  },
  {
    path: '/build/logView',
    name: 'LogView',
    view: 'build/LogView'
  }
]
