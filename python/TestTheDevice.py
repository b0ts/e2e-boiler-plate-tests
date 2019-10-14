import unittest
import subprocess

# run this from command line with $ python TestMBall.py
class TestFixture(unittest.TestCase):

    # normal mBall test with success of return 0
    def test_rolldice(self):
        print("test_rolldice starting")
        results = subprocess.call(['python','rolldice.py'])
        print("Results: ")
        print(results)
        self.assertEqual(results, 0)
        print("test_rolldice completed")

    # mBall force yes command via param
    def test_rolldice_force_yes(self):
        print("test_rolldice_force_yes starting")
        results = subprocess.call(['python','rolldice.py','forceYes'])
        print("Results: ")
        print(results)
        self.assertEqual(results, 0)
        print("test_rolldice_force_yes completed")

    # mBall force failure test by param
    def test_rolldirce_force_fail(self):
        print("test_rolldice_force_fail starting")
        results = subprocess.call(['python','rolldice.py','forceFailure'])
        print("Expecting Results of 1 and Actual Results: ")
        print(results)
        self.assertEqual(results, 1)
        print("test_rolldice_force_fail completed")

suite = unittest.TestLoader().loadTestsFromTestCase(TestFixture)
unittest.TextTestRunner(verbosity=2).run(suite)
