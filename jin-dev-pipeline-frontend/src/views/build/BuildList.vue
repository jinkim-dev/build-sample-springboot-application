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
                    @click="editItem(item)"
                  >mdi-pencil
                  </v-icon> 
                  <v-icon
                    small
                    @click="deleteItem(item)"
                  >
                    mdi-delete
                  </v-icon>
                </template>
              </td>
            </template>
          </v-data-table>
        </material-card>
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
    items: []
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
    deleteItem (item) {
      const index = this.items.indexOf(item)
      confirm('Are you sure you want to delete this build info?') && this.items.splice(index, 1)
    }
  }
}
</script>
