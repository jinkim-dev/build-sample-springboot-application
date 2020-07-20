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
          text="Job Build Log..."
        >
        
        <v-btn color="success" @click="sendMessage">
            sendMessage
          </v-btn>
          <Log :logs="logs"/>
        </material-card>
      </v-col>
      <v-col
        cols="12"
        md="8"
      >
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
  import Log from '../../components/build/Log'
  import axios from "axios";
  import SockJS from "sockjs-client";
  import Stomp from "webstomp-client";

  export default {
    components: {
      Log
    },
    props: {
      appId: Object,
      buildId: Object
    },
    data: () => ({
      logs: "",
      stompClient: null,
      appId: null,
      buildId: null
    }),
    methods: {
      connect: function() {
        let socket = new SockJS('http://localhost:8080/jindev/ws');
        let stompClient = Stomp.over(socket);
        let vm = this;
        stompClient.connect({}, function (e) {
          stompClient.subscribe(`/queue/builds/${vm.appId}`, function(val) {
            vm.logs += val.body;
          })
          stompClient.send(`/topic/builds/${vm.appId}/logs/${vm.buildId}`, {}, 'hello world');  
        })
        this.stompClient = stompClient;
      }
    },
    mounted: function() {
      this.appId = this.$route.query.appId;
      this.buildId = this.$route.query.buildId;
      this.$nextTick(function() {
        this.connect();
      })
    }
  }
</script>
