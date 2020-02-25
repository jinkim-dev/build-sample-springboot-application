<template>
  <v-container
    fill-height
    fluid
  >
    <v-row justify="center">
      <v-col
        cols="12"
        md="8"
      >
        <material-card
          color="green"
          title="Edit Profile"
          text="Complete your profile"
        >
          <v-form>
            <v-container class="py-0">
              <v-row>
                <v-col
                  cols="12"
                  md="4"
                >
                  <v-text-field
                    label="Build Tool"
                    v-model="buildTool"
                    disabled
                  ></v-text-field>
                </v-col>

                <v-col
                  cols="12"
                  md="4"
                >
                  <v-text-field
                    class="purple-input"
                    label="Git Address"
                    v-model="gitAddress"
                    disabled
                  />
                </v-col>

                <v-col
                  cols="12"
                  md="4"
                >
                  <v-text-field
                    label="target Server"
                    class="purple-input"
                    v-model="targetServer"
                    disabled
                  />
                </v-col>

                <v-col cols="12">
                  <v-textarea
                    class="purple-input"
                    label="Description"
                    v-model="description"
                    disabled
                  />
                </v-col>

                <v-col
                  cols="12"
                  class="text-right"
                >
                  <v-btn color="success">
                    Update Profile
                  </v-btn>
                </v-col>
              </v-row>
            </v-container>
          </v-form>
        </material-card>
      </v-col>
      <v-col
        cols="12"
        md="4"
      >
        <material-card class="v-card-profile">
          <v-avatar
            slot="offset"
            class="mx-auto d-block elevation-6"
            size="130"
          >
            <img
              src="https://demos.creative-tim.com/vue-material-dashboard/img/marc.aba54d65.jpg"
            >
          </v-avatar>
          <v-card-text class="text-center">
            <h6 class="overline mb-3">
              CEO / CO-FOUNDER
            </h6>

            <h4 class="font-weight-light">
              Alec Thompson
            </h4>

            <p class="font-weight-light">
              Don't be scared of the truth because we need to restart the human foundation in truth And I love you like Kanye loves Kanye I love Rick Owens’ bed design but the back is...
            </p>

            <v-btn color="success">
              Follow
            </v-btn>
          </v-card-text>
        </material-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  import axios from "axios";

  export default {
    data() {
      return {
        appName: '',
        description : '',
        buildTool : '',
        gitAddress : '',
        targetServer : ''
      }
    },
    mounted() {
      var name = this.$route.query.name;

      axios.get(`http://localhost:8080/jindev/builds/${name}`)
        .then(response => {
          console.info(response);
          var build = response.data.build;
          this.appName = build.appName;
          this.description = build.description;
          this.buildTool = build.buildTool;
          this.gitAddress = build.gitAddress;
          this.targetServer = build.targetServer;
        })
        .catch(function(error) {
          console.log(error);
        })
    }
  }
</script>
