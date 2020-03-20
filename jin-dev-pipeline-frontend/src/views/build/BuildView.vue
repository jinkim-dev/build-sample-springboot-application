<template>
  <v-container
    fluid
  >
    <v-row justify="center">
      <v-col
        cols="12"
        md="8"
      >
        <material-card
          color="green"
          :title="appName"
          text="Check the build information and the job list."
        >
          <v-form>
            <v-container class="py-0">
              <v-row>
                <v-col
                  cols="12"
                  md="4"
                >
                  <v-select
                    :items="buildToolItems"
                    label="Build Tool"
                    v-model="buildTool"
                    disabled
                  ></v-select>
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
                  <v-btn color="success" @click="build">
                    Build
                  </v-btn>
                  <v-snackbar
                    v-model="snackbar"
                  >
                    Buildling...
                    <v-btn
                      color="pink"
                      text
                      @click="snackbar = false"
                    >
                      Close
                    </v-btn>
                  </v-snackbar>
                </v-col>
              </v-row>
            </v-container>
          </v-form>
        </material-card>
      </v-col>
      <v-col
        cols="12"
        md="8"
      >
        <material-card>
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
                <td>{{ item.displayName }}</td>
                <td>{{ item.building? 'Building' : 'Finished' }}</td>
                <td>{{ item.duration / 1000 + ' sec' }}</td>
                <td>{{ item.result }}</td>                
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
        text: 'Name',
        value: 'displayName'
      },
      {
        sortable: false,
        text: 'Building',
        value: 'building'
      },
      {
        sortable: false,
        text: 'Duration',
        value: 'duration'
      },
      {
        sortable: false,
        text: 'Result',
        value: 'result'
      }
    ],
    items: [],
    id: 0,
    appName: '',
    description: '',
    buildTool: '',
    gitAddress: '',
    targetServer: '',
    snackbar: false,
    buildToolItems: ['maven', 'gradle']
    }),
    mounted() {
      this.id = this.$route.query.id;
      axios.get(`http://localhost:8080/jindev/builds/${this.id}`)
        .then(response => {
          console.info(response);
          var build = response.data.build;
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
    methods : {
      build() {
        console.info('build');
        axios.get(`http://localhost:8080/jindev/builds/${this.appName}/build`)
        .then(response => {
          this.snackbar = true;
        })
        .catch(function(error) {
          console.log(error);
        });
      }
    }
  }
</script>
