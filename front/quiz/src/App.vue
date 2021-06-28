<template>
 <div id="App">
    <link src="@/assets/logo.png"/>
    <router-view :key="$route.fullPath"/>
  </div>
</template>

<script>

export default {
  name: 'App',

  data: () => ({
    //
  }),
};
</script>
