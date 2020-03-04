<template>
  <v-container
    fill-height
    fluid
    grid-list-xl
  >
    <v-layout
      justify-center
      wrap
    >
      <v-flex
        md12
      >
      <div dir="rtl">
          <v-btn small color="teal" @click="$router.push('/build/BuildCreate')">Add Build Info</v-btn>
      </div>
        <material-card
          color="green"
          title="Build Table"
          text="You can check the registered build information here."
        >
          <v-data-table
            :headers="headers"
            :items="items"
            :items-per-page="5"
            class="elevation-1"
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
              <td><router-link :to="{ path: 'build/buildView', query: { name: item.appName }}">{{ item.appName }}</router-link></td>
              <td>{{ item.buildTool }}</td>
              <td>{{ item.gitAddress }}</td>
              <td>{{ item.targetServer }}</td>
              <td>
                <v-icon>{{ getLatestResult(item.latestBuildResult) }}</v-icon>{{ item.latestBuild }}</td>
              <td>
                <template v-slot:item.action="{ item }">
                  <v-icon
                    small
                    @click="confirmBuild(item)"
                  >mdi-arrow-right-drop-circle-outline
                  </v-icon>
                  <v-icon
                    small
                    @click="$router.push({name: 'BuildUpdate', query: { name: item.appName }})"
                  >mdi-pencil
                  </v-icon> 
                  <v-icon
                    small
                    @click="confirmDelete(item)"
                  >
                    mdi-delete
                  </v-icon>
                </template>
              </td>
            </template>
          </v-data-table>
        </material-card>
        <v-dialog
        v-model="buildDialog"
        max-width="290"
        >
          <v-card>
            <v-card-title class="headline"></v-card-title>
            <v-card-text>
              Are you sure you want to build?
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                color="green darken-1"
                text
                @click.stop="buildDialog = false"
              >
                No
              </v-btn>
              <v-btn
                color="green darken-1"
                text
                @click="build"
              >
                Yes
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
        <v-dialog
          v-model="deleteDialog"
          max-width="290"
        >
          <v-card>
            <v-card-title class="headline"></v-card-title>
            <v-card-text>
              Are you sure you want to delete this build info?
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                color="green darken-1"
                text
                @click="deleteDialog = false"
              >
                No
              </v-btn>
              <v-btn
                color="green darken-1"
                text
                @click="deleteBuild"
              >
                Yes
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
        <v-snackbar
          v-model="snackbar"
        >
          {{ message }}
          <v-btn
            color="pink"
            text
            @click="snackbar = false"
          >
            Close
          </v-btn>
        </v-snackbar>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  data: () => ({
    headers: [
      {
        sortable: false,
        text: 'Application Name',
        value: 'appName'
      },
      {
        sortable: false,
        text: 'Build Tool',
        value: 'buildTool'
      },
      {
        sortable: false,
        text: 'Git Address',
        value: 'gitAddress'
      },
      {
        sortable: false,
        text: 'Target Server',
        value: 'targetServer'
      },
      {
        sortable: false,
        text: 'Latest Build Reulst',
        value: 'latestBuildResult'
      },
      {
        sortable: false,
        text: 'Actions',
        value: 'action'
      }
    ],
    items: [],
    buildDialog: false,
    deleteDialog: false,
    buildItem: {},
    deleteItem: {},
    snackbar: false,
    message: ''
  }),
  mounted() {
    axios.get('http://localhost:8080/jindev/builds')
    .then(response => {
      this.items = response.data.builds;
    })
    .catch(function(error) {
      console.log(error);
    })
  },
  methods: {
    getLatestResult(result) {
      return result === 'SUCCESS'? 'mdi-check' : 'mdi-close'
    },
    build() {
      
      axios.get(`http://localhost:8080/jindev/builds/${this.buildItem.appName}/build`)
      .then(response => {
        this.buildDialog = false;
        this.message = 'Building...';
        this.snackbar = true;
      })
      .catch(function(error) {
        console.log(error);
      });
    },
    deleteBuild () {
      axios.delete(`http://localhost:8080/jindev/builds/${this.deleteItem.id}`)
          .then(() => {
            const index = this.items.indexOf(this.deleteItem);
            this.deleteDialog = false;
            this.message = 'Deletion succeeded.';
            this.snackbar = true;
            this.items.splice(index, 1);
          }).catch((error) => {
            console.info(error);
          })
    },
    confirmBuild (item) {
      this.buildDialog = true;
      this.buildItem = item;
    },
    confirmDelete (item) {
      this.deleteDialog = true;
      this.deleteItem = item;
    }
  }
}
</script>
