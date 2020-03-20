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
      id: 0,
      appName: '',
      description: '',
      buildTool: '',
      gitAddress: '',
      targetServer: ''
    }),
    created() {
        this.fetch();
    },
    methods: {
      fetch() {
        this.id = this.$route.query.id;
        console.log(`id : ${this.id}`);
        axios.get(`http://localhost:8080/jindev/builds/${this.id}`)
            .then(result => {
                console.info(this);
                const build = result.data.build;
                this.appName = build.appName;
                this.description = build.description;
                this.buildTool = build.buildTool;
                this.gitAddress = build.gitAddress;
                this.targetServer = build.targetServer;
                this.items = build.builds;
            })
            .catch(function(error) {
                console.log(error);
            })
      },
      save(build) {
        axios.put(`http://localhost:8080/jindev/builds/${this.id}`, build)
        .then(response => {
          window.location.href = '/';
        }).catch(function(error) {
          console.log(error);
        })
      }
    }
  }
</script>
