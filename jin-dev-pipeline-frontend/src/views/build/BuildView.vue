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
          v-bind:title="appName"
          text="Check the build information and the job list."
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
        md="12"
      >
        <material-card class="v-card-profile">
          <v-avatar
            slot="offset"
            class="mx-auto d-block elevation-6"
            size="130"
          >
            <img
              src="../../../public/history.png"
            >
          </v-avatar>

          
          <v-card-text class="text-center">

            <h4 class="font-weight-light">
              History
            </h4>

            <p class="font-weight-light">
              Don't be scared of the truth because we need to restart the human foundation in truth And I love you like Kanye loves Kanye I love Rick Owens’ bed design but the back is...
            </p>

            <v-data-table
              :headers="headers"
              :items="items"
              hide-actions
            >
              <template
                slot="headerCell"
                slot-scope="{ header }"
              >
                <span
                  class="subheading font-weight-light text-success text--darken-3"
                  v-text="header.text"
                />
              </template>
              <template
                slot="items"
                slot-scope="{ item }"
              >
                <td>{{ item.number }}</td>
                <td>{{ item.status }}</td>
                <td>{{ item.buildDate }}</td>
              </template>
            </v-data-table>
          </v-card-text>
        </material-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  import axios from "axios";

  export default {
    data: () => ({
      headers: [
      {
        sortable: false,
        text: 'Number',
        value: 'number'
      },
      {
        sortable: false,
        text: 'Status',
        value: 'status'
      },
      {
        sortable: false,
        text: 'Build Date',
        value: 'buildDate'
      }
    ],
    items: [
      {
        number: '1',
        status: 'SUCCESS',
        buildDate: '2020-01-01'
      }
    ],
        appName: '',
        description : '',
        buildTool : '',
        gitAddress : '',
        targetServer : ''
    }),
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
