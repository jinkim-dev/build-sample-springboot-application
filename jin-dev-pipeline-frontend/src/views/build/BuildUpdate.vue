<template>
  <build :saveType="saveType"
         :appName="appName"
         :description="description"
         :buildTool="buildTool"
         :gitAddress="gitAddress"
         :targetServer="targetServer"
   @submit="save"></build>
</template>

<script>
  import Build from "../../components/build/Build";
  import axios from "axios";
  export default {
    components: {Build},
    data: () => ({
      saveType : 'edit',
      appName: '',
      description: '',
      buildTool: '',
      gitAddress: '',
      targetServer: ''
    }),
    mounted() {
        var name = this.$route.query.name;
        // this.appName = 'jpetstore';
        let that = this;
        axios.get(`http://localhost:8080/jindev/builds/${name}`)
            .then(result => {
              debugger
                var build = result.data.build;
                that.appName = build.appName;
                // this.description = build.description;
                // this.buildTool = build.buildTool;
                // this.gitAddress = build.gitAddress;
                // this.targetServer = build.targetServer;
                // this.items = build.builds;
            })
            .catch(function(error) {
                console.log(error);
            })
    },
    methods: {
      save() {
        axios.put(`'http://localhost:8080/jindev/builds/${appName}`, {
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
