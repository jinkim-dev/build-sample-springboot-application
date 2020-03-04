<template>
  <v-container
    id="build"
    fluid
  >
    <v-row justify="center">
      <v-col
        cols="12"
        md="12"
      >
        <material-card
          color="green"
          :title="title"
          text="Complete Build Info"
        >
          <v-form>
            <v-container class="py-0">
              <v-row>
                <v-col
                  cols="12"
                  md="4"
                >
                  <v-text-field
                    class="purple-input"
                    label="App Name"
                    v-model="appName"
                  />
                </v-col>

                <v-col
                  cols="12"
                  md="4"
                >
                  <v-text-field
                    class="purple-input"
                    label="Description"
                    v-model="description"
                  />
                </v-col>

                <v-col
                  cols="12"
                  md="4"
                >
                  <v-text-field
                    class="purple-input"
                    label="Build Tool"
                    v-model="buildTool"
                  />
                </v-col>

                <v-col
                  cols="12"
                  md="4"
                >
                  <v-text-field
                    class="purple-input"
                    label="Git Address"
                    v-model="gitAddress"
                  />
                </v-col>

                <v-col
                  cols="12"
                  md="4"
                >
                  <v-text-field
                    class="purple-input"
                    label="Target Server"
                    v-model="targetServer"
                  />
                </v-col>
                <v-col
                  cols="12"
                  md="4"
                  >
                  <v-btn color="success" @click="submit">
                    {{ saveType }}
                  </v-btn>
                </v-col>
              </v-row>
            </v-container>
          </v-form>
        </material-card>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
export default {
    props: [
      'saveType',
      'appName',
      'description',
      'buildTool',
      'gitAddress',
      'targetServer'
    ],
    data: {
        saveType: '',
        appName: '',
        description : '',
        buildTool : '',
        gitAddress : '',
        targetServer : ''
    },
    computed: {
        title() {
            return this.saveType === 'add'? 'Add Build Info' : 'Update Build Info';
        }
    },
    methods: {
        submit() {
            this.$emit('submit');
        }
    }
}
</script>