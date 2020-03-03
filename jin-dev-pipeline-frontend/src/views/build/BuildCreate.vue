<template>
  <build :saveType="saveType" @submit="save"></build>
</template>

<script>
  import Build from "../../components/build/Build";
  import axios from "axios";
  export default {
    components: {Build},
    data: () => ({
      saveType : 'add'
    }),
    methods: {
      save() {
        axios.post('http://localhost:8080/jindev/builds', {
          appName : this.appName,
          description : this.description,
          buildTool : this.buildTool,
          gitAddress : this.gitAddress,
          targetServer : this.targetServer
        })
        .then(response => {
          window.location.href = '/';
        }).catch(function(error) {
          console.log(error);
        })
      }
    }
  }
</script>
