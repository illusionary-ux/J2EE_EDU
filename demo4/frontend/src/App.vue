<template>
  <div id="app">
    <nav>
      <router-link to="/users">User List</router-link> |
      <router-link to="/add-user">Add User</router-link>
    </nav>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App'
};
</script>

<style scoped>
nav {
  padding: 1rem;
}

nav a {
  margin-right: 1rem;
  text-decoration: none;
  color: #42b983;
}

nav a.router-link-exact-active {
  font-weight: bold;
}
</style>



