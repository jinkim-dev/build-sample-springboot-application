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
                <v-icon>{{ getLatestResult(item.latestBuild) }}</v-icon>{{ item.latestBuild }}</td>
              <td>
                <template v-slot:item.action="{ item }">
                  <v-icon
                    small
                    @click="build(item)"
                  >mdi-arrow-right-drop-circle-outline
                  </v-icon>
                  <v-icon
                    small
                    @click.stop="runDialog = true"
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
        v-model="runDialog"
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
                @click="runDialog = false"
              >
                No
              </v-btn>
              <v-btn
                color="green darken-1"
                text
                @click="runDialog = false"
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
        text: 'Latest Build',
        value: 'latestBuildResult'
      },
      {
        sortable: false,
        text: 'Actions',
        value: 'action'
      }
    ],
    items: [],
    runDialog: false,
    deleteDialog: false,
    deleteItem: {}
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
      return result === 'Success' ? 'mdi-check' : 'mdi-close'
    },
    build () {
      window.location.href = "/build-save"
    },
    editItem (item) {
      const index = this.items.indexOf(item)
      confirm('Are you sure you want to build?')
    },
    confirmDelete (item) {
      this.deleteDialog = true;
      this.deleteItem = item;
    },
    deleteBuild () {
      console.info('deleteItem');
      const index = this.items.indexOf(this.deleteItem);
      this.deleteDialog = false;
      this.items.splice(index, 1);
      // confirm('Are you sure you want to delete this build info?') && this.items.splice(index, 1)
    }
  }
}
</script>
