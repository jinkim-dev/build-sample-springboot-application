<template>
  <build :saveType="saveType" @submit="save"></build>
</template>

<script>
  import Build from "../../components/build/Build";
  import axios from "axios";
  export default {
    components: {Build},
    data: () => ({
      saveType: 'add',
      appName: '',
      description: '',
      buildTool: '',
      gitAddress: '',
      targetServer: ''
    }),
    methods: {
      save(build) {
        axios.post('http://localhost:8080/jindev/builds', {
          appName : this.build.appName,
          description : this.build.description,
          buildTool : this.build.buildTool,
          gitAddress : this.build.gitAddress,
          targetServer : this.build.targetServer
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
