<template>
  <v-footer
    id="core-footer"
    absolute
    height="82"
  >
    <div class="footer-items">
      <span
        v-for="link in links"
        :key="link.name"
      >
        <a
          :href="link.Link"
          class="tertiary--text footer-links">{{ link.name }}</a>
      </span>
    </div>
    <v-spacer/>
    <span class="font-weight-light copyright">
      &copy;
      {{ (new Date()).getFullYear() }}
      <a
        href="https://github.com/jinkim-dev/build-sample-springboot-application"
        target="_blank">Jindev</a>
    </span>
  </v-footer>
</template>

<script>
export default {
  data: () => ({
    links: [
      { name: 'Home', Link: '/#' },
      { name: 'Git', Link: 'https://github.com/jinkim-dev/build-sample-springboot-application' }
    ]
  })
}
</script>

<style>
#core-footer {
  z-index: 0;
}
</style>
