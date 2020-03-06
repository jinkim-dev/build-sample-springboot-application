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
                    v-model="build.appName"
                  />
                </v-col>

                <v-col
                  cols="12"
                  md="4"
                >
                  <v-text-field
                    class="purple-input"
                    label="Description"
                    v-model="build.description"
                  />
                </v-col>

                <v-col
                  cols="12"
                  md="4"
                >
                  <v-text-field
                    class="purple-input"
                    label="Build Tool"
                    v-model="build.buildTool"
                  />
                </v-col>

                <v-col
                  cols="12"
                  md="4"
                >
                  <v-text-field
                    class="purple-input"
                    label="Git Address"
                    v-model="build.gitAddress"
                  />
                </v-col>

                <v-col
                  cols="12"
                  md="4"
                >
                  <v-text-field
                    class="purple-input"
                    label="Target Server"
                    v-model="build.targetServer"
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
        build: {}
    },
    // 부모 컴포넌트에서 props로 받은 값을 자식컴포넌트로 바꾼 뒤 사용하면 에러가 발생하지 않는다.
    // 위 data()안에 있는 build에 담기
    created() {
      this.build.appName = this.appName;
      this.build.description = this.description;
      this.build.buildTool = this.buildTool;
      this.build.gitAddress = this.gitAddress;
      this.build.targetServer = this.targetServer;
    },
    computed: {
        title() {
            return this.saveType === 'add'? 'Add Build Info' : 'Update Build Info';
        }
    },
    methods: {
        submit() {
            // $emit() : 자식 컴포넌트에서 부모 컴포넌트로 보냄
            this.$emit('submit', this.build);
        }
    }
}
</script>