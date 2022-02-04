/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import LocalDesejadoUpdateComponent from '@/entities/local-desejado/local-desejado-update.vue';
import LocalDesejadoClass from '@/entities/local-desejado/local-desejado-update.component';
import LocalDesejadoService from '@/entities/local-desejado/local-desejado.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('LocalDesejado Management Update Component', () => {
    let wrapper: Wrapper<LocalDesejadoClass>;
    let comp: LocalDesejadoClass;
    let localDesejadoServiceStub: SinonStubbedInstance<LocalDesejadoService>;

    beforeEach(() => {
      localDesejadoServiceStub = sinon.createStubInstance<LocalDesejadoService>(LocalDesejadoService);

      wrapper = shallowMount<LocalDesejadoClass>(LocalDesejadoUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          localDesejadoService: () => localDesejadoServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.localDesejado = entity;
        localDesejadoServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(localDesejadoServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.localDesejado = entity;
        localDesejadoServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(localDesejadoServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundLocalDesejado = { id: 123 };
        localDesejadoServiceStub.find.resolves(foundLocalDesejado);
        localDesejadoServiceStub.retrieve.resolves([foundLocalDesejado]);

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
