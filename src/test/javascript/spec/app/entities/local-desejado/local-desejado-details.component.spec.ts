/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import LocalDesejadoDetailComponent from '@/entities/local-desejado/local-desejado-details.vue';
import LocalDesejadoClass from '@/entities/local-desejado/local-desejado-details.component';
import LocalDesejadoService from '@/entities/local-desejado/local-desejado.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('LocalDesejado Management Detail Component', () => {
    let wrapper: Wrapper<LocalDesejadoClass>;
    let comp: LocalDesejadoClass;
    let localDesejadoServiceStub: SinonStubbedInstance<LocalDesejadoService>;

    beforeEach(() => {
      localDesejadoServiceStub = sinon.createStubInstance<LocalDesejadoService>(LocalDesejadoService);

      wrapper = shallowMount<LocalDesejadoClass>(LocalDesejadoDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { localDesejadoService: () => localDesejadoServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundLocalDesejado = { id: 123 };
        localDesejadoServiceStub.find.resolves(foundLocalDesejado);

        // WHEN
        comp.retrieveLocalDesejado(123);
        await comp.$nextTick();

        // THEN
        expect(comp.localDesejado).toBe(foundLocalDesejado);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundLocalDesejado = { id: 123 };
        localDesejadoServiceStub.find.resolves(foundLocalDesejado);

        // WHEN
        comp.beforeRouteEnter({ params: { localDesejadoId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.localDesejado).toBe(foundLocalDesejado);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
