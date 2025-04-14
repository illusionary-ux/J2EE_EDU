<template>
  <div v-if="user">
    <h1>User Details</h1>
    <img :src="user.photoUrl" alt="User Photo" class="user-photo">
    <p><strong>Name:</strong> {{ user.name }}</p>
    <p><strong>Email:</strong> {{ user.email }}</p>
    <button @click="goBack">Back to List</button>
  </div>
  <div v-else>
    <p>Loading...</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      user: null
    };
  },
  created() {
    this.fetchUser();
  },
  methods: {
    fetchUser() {
      const userId = this.$route.params.id;
      axios.get(`/api/user/${userId}`)
        .then(response => {
          this.user = response.data;
        })
        .catch(error => {
          console.error('There was an error fetching the user!', error);
        });
    },
    goBack() {
      this.$router.push({ name: 'UserList' });
    }
  }
};
</script>

<style scoped>
.user-photo {
  width: 150px;
  height: 150px;
  border-radius: 50%;
  object-fit: cover;
  margin-bottom: 1rem;
}
</style>



