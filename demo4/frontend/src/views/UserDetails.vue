<template>
  <div>
    <h1>User List</h1>
    <ul>
      <li v-for="user in users" :key="user.uid">
        <router-link :to="{ name: 'UserDetails', params: { id: user.uid } }">
          {{ user.name }} ({{ user.email }})
        </router-link>
      </li>
    </ul>
    <button @click="fetchUsers">Refresh Users</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      users: []
    };
  },
  created() {
    this.fetchUsers();
  },
  methods: {
    fetchUsers() {
      axios.get('/api/users')
        .then(response => {
          this.users = response.data;
        })
        .catch(error => {
          console.error('There was an error fetching the users!', error);
        });
    }
  }
};
</script>

<style scoped>
ul {
  list-style-type: none;
  padding: 0;
}

li {
  margin: 1rem 0;
}

a {
  text-decoration: none;
  color: #42b983;
}

button {
  margin-top: 1rem;
}
</style>



