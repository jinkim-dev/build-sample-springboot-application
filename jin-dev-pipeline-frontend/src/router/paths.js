/**
 * Define all of your application routes here
 * for more information on routes, see the
 * official documentation https://router.vuejs.org/en/
 */
export default [
  {
    path: '/',
    name: 'Build List',
    view: 'build/BuildList'
  },
  {
    path: '/build/BuildCreate',
    name: 'Build Create',
    view: 'build/BuildCreate'
  },
  {
    path: '/build/buildView',
    name: 'Build View',
    view: 'build/BuildView'
  }
]
