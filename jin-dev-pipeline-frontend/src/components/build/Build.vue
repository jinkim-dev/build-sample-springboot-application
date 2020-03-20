<template>
  <v-container
    id="build"
    fluid
  >
    <v-row justify="center">
      <v-col
        cols="12"
        md="4"
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
                    :disabled="this.saveType === 'edit'"
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
                  <v-select
                    class="purple-input"
                    label="Build Tool"
                    v-model="buildTool"
                    :items="buildToolItems"
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
  // 부모에게 받은 값을 바로 가공하면 에러발생. 가공 필요
    props: {
      saveType: String,
      appName: String,
      description: String,
      buildTool: String,
      gitAddress: String,
      targetServer: String
    },
    data() {
      return {
        buildToolItems: ['maven', 'gradle']
      }
    },
    computed: {
        title() {
            return this.saveType === 'add'? 'Add Build Info' : 'Update Build Info';
        }
    },
    methods: {
        submit() {
            // $emit() : 자식 컴포넌트에서 부모 컴포넌트로 보냄
            this.$emit('submit', {
              appName : this.appName,
              description : this.description,
              buildTool : this.buildTool,
              gitAddress : this.gitAddress,
              targetServer : this.targetServer
          });
        }
    }
}
</script>