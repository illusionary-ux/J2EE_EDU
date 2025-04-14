import Vue from 'vue';
import Router from 'vue-router';
import UserList from '@/views/UserList.vue';
import UserDetails from '@/views/UserDetails.vue';
import UserForm from '@/views/UserForm.vue';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/users'
    },
    {
      path: '/users',
      name: 'UserList',
      component: UserList
    },
    {
      path: '/user/:id',
      name: 'UserDetails',
      component: UserDetails
    },
    {
      path: '/add-user',
      name: 'UserForm',
      component: UserForm
    }
  ]
});



