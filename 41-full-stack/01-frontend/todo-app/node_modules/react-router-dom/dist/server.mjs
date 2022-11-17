import * as React from 'react';
import { Action, invariant, UNSAFE_convertRoutesToDataRoutes, IDLE_NAVIGATION, IDLE_FETCHER } from '@remix-run/router';
import { parsePath, Router, UNSAFE_DataStaticRouterContext, UNSAFE_DataRouterContext, UNSAFE_DataRouterStateContext, Routes, createPath } from 'react-router-dom';

/**
 * A <Router> that may not navigate to any other location. This is useful
 * on the server where there is no stateful UI.
 */
function StaticRouter({
  basename,
  children,
  location: locationProp = "/"
}) {
  if (typeof locationProp === "string") {
    locationProp = parsePath(locationProp);
  }

  let action = Action.Pop;
  let location = {
    pathname: locationProp.pathname || "/",
    search: locationProp.search || "",
    hash: locationProp.hash || "",
    state: locationProp.state || null,
    key: locationProp.key || "default"
  };
  let staticNavigator = getStatelessNavigator();
  return /*#__PURE__*/React.createElement(Router, {
    basename: basename,
    children: children,
    location: location,
    navigationType: action,
    navigator: staticNavigator,
    static: true
  });
}

/**
 * A Data Router that may not navigate to any other location. This is useful
 * on the server where there is no stateful UI.
 */
function unstable_StaticRouterProvider({
  basename,
  context,
  router,
  hydrate = true,
  nonce
}) {
  !(router && context) ? process.env.NODE_ENV !== "production" ? invariant(false, "You must provide `router` and `context` to <StaticRouterProvider>") : invariant(false) : void 0;
  let dataRouterContext = {
    router,
    navigator: getStatelessNavigator(),
    static: true,
    basename: basename || "/"
  };
  let hydrateScript = "";

  if (hydrate !== false) {
    let data = {
      loaderData: context.loaderData,
      actionData: context.actionData,
      errors: context.errors
    }; // Use JSON.parse here instead of embedding a raw JS object here to speed
    // up parsing on the client.  Dual-stringify is needed to ensure all quotes
    // are properly escaped in the resulting string.  See:
    //   https://v8.dev/blog/cost-of-javascript-2019#json

    let json = JSON.stringify(JSON.stringify(data));
    hydrateScript = `window.__staticRouterHydrationData = JSON.parse(${json});`;
  }

  return /*#__PURE__*/React.createElement(React.Fragment, null, /*#__PURE__*/React.createElement(UNSAFE_DataStaticRouterContext.Provider, {
    value: context
  }, /*#__PURE__*/React.createElement(UNSAFE_DataRouterContext.Provider, {
    value: dataRouterContext
  }, /*#__PURE__*/React.createElement(UNSAFE_DataRouterStateContext.Provider, {
    value: dataRouterContext.router.state
  }, /*#__PURE__*/React.createElement(Router, {
    basename: dataRouterContext.basename,
    location: dataRouterContext.router.state.location,
    navigationType: dataRouterContext.router.state.historyAction,
    navigator: dataRouterContext.navigator
  }, /*#__PURE__*/React.createElement(Routes, null))))), hydrateScript ? /*#__PURE__*/React.createElement("script", {
    suppressHydrationWarning: true,
    nonce: nonce,
    dangerouslySetInnerHTML: {
      __html: hydrateScript
    }
  }) : null);
}

function getStatelessNavigator() {
  return {
    createHref(to) {
      return typeof to === "string" ? to : createPath(to);
    },

    push(to) {
      throw new Error(`You cannot use navigator.push() on the server because it is a stateless ` + `environment. This error was probably triggered when you did a ` + `\`navigate(${JSON.stringify(to)})\` somewhere in your app.`);
    },

    replace(to) {
      throw new Error(`You cannot use navigator.replace() on the server because it is a stateless ` + `environment. This error was probably triggered when you did a ` + `\`navigate(${JSON.stringify(to)}, { replace: true })\` somewhere ` + `in your app.`);
    },

    go(delta) {
      throw new Error(`You cannot use navigator.go() on the server because it is a stateless ` + `environment. This error was probably triggered when you did a ` + `\`navigate(${delta})\` somewhere in your app.`);
    },

    back() {
      throw new Error(`You cannot use navigator.back() on the server because it is a stateless ` + `environment.`);
    },

    forward() {
      throw new Error(`You cannot use navigator.forward() on the server because it is a stateless ` + `environment.`);
    }

  };
}

function unstable_createStaticRouter(routes, context) {
  let dataRoutes = UNSAFE_convertRoutesToDataRoutes(routes);

  let msg = method => `You cannot use router.${method}() on the server because it is a stateless environment`;

  return {
    get basename() {
      return "/";
    },

    get state() {
      return {
        historyAction: Action.Pop,
        location: context.location,
        matches: context.matches,
        loaderData: context.loaderData,
        actionData: context.actionData,
        errors: context.errors,
        initialized: true,
        navigation: IDLE_NAVIGATION,
        restoreScrollPosition: null,
        preventScrollReset: false,
        revalidation: "idle",
        fetchers: new Map()
      };
    },

    get routes() {
      return dataRoutes;
    },

    initialize() {
      throw msg("initialize");
    },

    subscribe() {
      throw msg("subscribe");
    },

    enableScrollRestoration() {
      throw msg("enableScrollRestoration");
    },

    navigate() {
      throw msg("navigate");
    },

    fetch() {
      throw msg("fetch");
    },

    revalidate() {
      throw msg("revalidate");
    },

    createHref() {
      throw msg("createHref");
    },

    getFetcher() {
      return IDLE_FETCHER;
    },

    deleteFetcher() {
      throw msg("deleteFetcher");
    },

    dispose() {
      throw msg("dispose");
    },

    _internalFetchControllers: new Map(),
    _internalActiveDeferreds: new Map()
  };
}

export { StaticRouter, unstable_StaticRouterProvider, unstable_createStaticRouter };
