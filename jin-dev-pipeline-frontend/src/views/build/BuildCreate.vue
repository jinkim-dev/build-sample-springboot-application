<template>
  <build :saveType="saveType" :appName="appName" :description="description"
   :buildTool="buildTool" :gitAddress="gitAddress" :targetServer="targetServer" @submit="save"></build>
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
          appName : build.appName,
          description : build.description,
          buildTool : build.buildTool,
          gitAddress : build.gitAddress,
          targetServer : build.targetServer
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
